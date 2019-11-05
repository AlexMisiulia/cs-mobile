package com.binarysages.mobile.app.corespirit.models.events

import com.google.gson.annotations.SerializedName

data class Point(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double
)