package com.syfuzzaman.test_project_gozayaan.ui.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import java.io.IOException
import java.util.*

class LocationHelper(private val context: Context) {
    private lateinit var locationManager: LocationManager
    private var gpsLocationListener: LocationListener? = null
    private var networkLocationListener: LocationListener? = null

    fun initializeLocationManager() {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    fun isLocationPermissionGranted(): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun isProviderEnabled(): Boolean {
        val hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        return hasGps || hasNetwork
    }

    fun fetchLocation(
        onLocationFetched: (latitude: Double, longitude: Double, address: String) -> Unit,
        onError: (message: String) -> Unit
    ) {
        val hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!hasGps && !hasNetwork) {
            onError("Location Off")
            return
        }

        if (!isLocationPermissionGranted()) {
            onError("Permission not granted")
            return
        }

        try {
            val lastGpsLocation = if (hasGps) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            } else null
            val lastNetworkLocation = if (hasNetwork) {
                locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            } else null

            val bestLocation = when {
                lastGpsLocation != null && lastNetworkLocation != null -> {
                    if (lastGpsLocation.accuracy > lastNetworkLocation.accuracy) lastGpsLocation else lastNetworkLocation
                }
                lastGpsLocation != null -> lastGpsLocation
                lastNetworkLocation != null -> lastNetworkLocation
                else -> null
            }

            if (bestLocation != null) {
                val address = getAddressFromCoordinates(bestLocation.latitude, bestLocation.longitude)
                onLocationFetched(bestLocation.latitude, bestLocation.longitude, address)
            } else {
                // Request location updates if no last known location is available
                requestSingleUpdate(
                    hasGps,
                    hasNetwork,
                    onLocationFetched,
                    onError
                )
            }
        } catch (e: Exception) {
            onError("Something went wrong")
        }
    }

    private fun requestSingleUpdate(
        hasGps: Boolean,
        hasNetwork: Boolean,
        onLocationFetched: (latitude: Double, longitude: Double, address: String) -> Unit,
        onError: (message: String) -> Unit
    ) {
        try {
            if (hasGps) {
                gpsLocationListener = object : LocationListener {
                    override fun onLocationChanged(location: Location) {
                        val address = getAddressFromCoordinates(location.latitude, location.longitude)
                        onLocationFetched(location.latitude, location.longitude, address)
                        locationManager.removeUpdates(this)
                    }

                    override fun onProviderEnabled(provider: String) {}
                    override fun onProviderDisabled(provider: String) {}
                }
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    5000L,
                    0f,
                    gpsLocationListener!!
                )
            }

            if (hasNetwork) {
                networkLocationListener = object : LocationListener {
                    override fun onLocationChanged(location: Location) {
                        val address = getAddressFromCoordinates(location.latitude, location.longitude)
                        onLocationFetched(location.latitude, location.longitude, address)
                        locationManager.removeUpdates(this)
                    }

                    override fun onProviderEnabled(provider: String) {}
                    override fun onProviderDisabled(provider: String) {}
                }
                locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    5000L,
                    0f,
                    networkLocationListener!!
                )
            }
        } catch (e: Exception) {
            onError("Error requesting location updates")
        }
    }

    private fun getAddressFromCoordinates(latitude: Double, longitude: Double): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        return try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses?.isNotEmpty() == true) {
                val address = addresses[0]
                "${address.locality}, ${address.countryName}"
            } else {
                "Unknown"
            }
        } catch (e: IOException) {
            "Unable to get address"
        }
    }

    fun cleanup() {
        gpsLocationListener?.let { locationManager.removeUpdates(it) }
        networkLocationListener?.let { locationManager.removeUpdates(it) }
    }
}
