package com.binarysages.mobile.app.corespirit.models.practitioners

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class LocationPoint(
    @SerializedName("x") val x: Float?,
    @SerializedName("y") val y: Float?
) : Parcelable