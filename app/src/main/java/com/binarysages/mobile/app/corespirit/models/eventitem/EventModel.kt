package com.binarysages.mobile.app.corespirit.models.eventitem

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventModel(
    @SerializedName("error") val error: String,
    @SerializedName("data") val data: Data
) : Parcelable