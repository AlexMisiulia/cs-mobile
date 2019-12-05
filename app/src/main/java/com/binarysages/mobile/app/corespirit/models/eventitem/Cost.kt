package com.binarysages.mobile.app.corespirit.models.eventitem

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cost(
    @SerializedName("currency") val currency: String,
    @SerializedName("value") val value: Int
) : Parcelable