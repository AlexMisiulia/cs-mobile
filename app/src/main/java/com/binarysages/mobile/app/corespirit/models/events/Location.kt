package com.binarysages.mobile.app.corespirit.models.events

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("address") val address: String,
    @SerializedName("point") val point: Point
)