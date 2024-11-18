package com.syfuzzaman.test_project_gozayaan.data.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HotelInfo(
    @SerializedName("property_name") val propertyName: String? = null,
    @SerializedName("location") val location: String? = null,
    @SerializedName("rating") val rating: Double? = 0.0,
    @SerializedName("description") val description: String? = null,
    @SerializedName("fare") val fare: Double? = 0.0,
    @SerializedName("fare_unit") val fareUnit: String? = null,
    @SerializedName("is_available") val isAvailable: Boolean? = null,
    @SerializedName("hero_image") val heroImage: String? = null,
    @SerializedName("detail_images") val detailImages: List<String> = listOf(),
    @SerializedName("currency") val currency: String? = null,

    // Custom fields
    var isBookmarked: Boolean = false
): Parcelable
