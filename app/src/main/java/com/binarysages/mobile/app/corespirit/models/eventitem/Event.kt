package com.binarysages.mobile.app.corespirit.models.eventitem

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("isSeries") val isSeries: Boolean,
    @SerializedName("isOnline") val isOnline: Boolean,
    @SerializedName("relationship") val relationship: Int,
    @SerializedName("tickets") val tickets: List<Tickets>,
    @SerializedName("location") val location: Location,
    @SerializedName("organizer") val organizer: Organizer,
    @SerializedName("startAt") val startAt: String,
    @SerializedName("endAt") val endAt: String,
    @SerializedName("categoryId") val categoryId: Int,
    @SerializedName("image") val image: String
) : Parcelable