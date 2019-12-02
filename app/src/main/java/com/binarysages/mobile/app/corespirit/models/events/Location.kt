package com.binarysages.mobile.app.corespirit.models.events

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    @SerializedName("address") val address: String,
    @SerializedName("point") val point: Point
) : Parcelable