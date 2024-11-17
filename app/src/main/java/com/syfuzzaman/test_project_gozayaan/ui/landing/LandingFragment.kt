package com.syfuzzaman.test_project_gozayaan.ui.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.permissionx.guolindev.PermissionX
import com.syfuzzaman.test_project_gozayaan.databinding.FragmentLandingBinding
import com.syfuzzaman.test_project_gozayaan.ui.utils.LocationHelper

class LandingFragment : Fragment() {
    private var _binding: FragmentLandingBinding? = null
    private val binding get() = _binding
    private lateinit var locationHelper: LocationHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLandingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationHelper = LocationHelper(requireContext())
        locationHelper.initializeLocationManager()
        checkAndFetchLocation()

        binding?.topAppBar?.currentLocation?.setOnClickListener {
            requestLocationPermission()
        }
    }

    private fun checkAndFetchLocation() {
        if (locationHelper.isLocationPermissionGranted()) {
            fetchLocation()
        } else {
            binding?.topAppBar?.currentLocation?.text = "Tap to see location"
        }
    }

    private fun requestLocationPermission() {
        PermissionX.init(this)
            .permissions(android.Manifest.permission.ACCESS_COARSE_LOCATION)
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(
                    deniedList,
                    "Location permission is required to display your current location.",
                    "OK",
                    "Cancel"
                )
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(
                    deniedList,
                    "Please enable location permission in Settings.",
                    "Go to Settings",
                    "Cancel"
                )
            }
            .request { allGranted, _, _ ->
                if (allGranted) {
                    fetchLocation()
                } else {
                    binding?.topAppBar?.currentLocation?.text = "Tap to see location"
                }
            }
    }

    private fun fetchLocation() {
        locationHelper.fetchLocation(
            onLocationFetched = { _, _, address ->
                binding?.topAppBar?.currentLocation?.text = address
            },
            onError = { message ->
                binding?.topAppBar?.currentLocation?.text = message
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        locationHelper.cleanup()
    }
}
