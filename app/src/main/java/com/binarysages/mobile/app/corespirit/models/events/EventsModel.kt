package com.binarysages.mobile.app.corespirit.models.events

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventsModel(
    @SerializedName("error") val error: String,
    @SerializedName("data") val data: Data
) : Parcelable