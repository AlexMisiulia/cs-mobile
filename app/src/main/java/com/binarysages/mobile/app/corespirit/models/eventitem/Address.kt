package com.binarysages.mobile.app.corespirit.models.eventitem

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address (
    @SerializedName("country") val country: String,
    @SerializedName("city") val city: String,
    @SerializedName("name") val name: String,
    @SerializedName("postal_code") val postal_code: String
) : Parcelable