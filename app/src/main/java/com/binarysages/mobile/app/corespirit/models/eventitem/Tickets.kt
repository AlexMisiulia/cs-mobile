package com.binarysages.mobile.app.corespirit.models.eventitem

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tickets(
    @SerializedName("cost") val cost: Cost,
    @SerializedName("free") val free: Boolean,
    @SerializedName("name") val name: String
) : Parcelable