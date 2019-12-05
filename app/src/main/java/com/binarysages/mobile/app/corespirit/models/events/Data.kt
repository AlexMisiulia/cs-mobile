package com.binarysages.mobile.app.corespirit.models.events

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @SerializedName("events") val events: MutableList<Event>,
    @SerializedName("pageInfo") val pageInfo: PageInfo
) : Parcelable