package com.binarysages.mobile.app.corespirit.models.events

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Point(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double
) : Parcelable