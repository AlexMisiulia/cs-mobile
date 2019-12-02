package com.binarysages.mobile.app.corespirit.models.events

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Organizer(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String
) : Parcelable