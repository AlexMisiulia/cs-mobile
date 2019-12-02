package com.binarysages.mobile.app.corespirit.models.events

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    @SerializedName("id") val id: Int,
    @SerializedName("start_dt") val start_dt: String,
    @SerializedName("end_dt") val end_dt: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("online") val online: Boolean,
    @SerializedName("relationship") val relationship: Int,
    @SerializedName("free") val free: Boolean,
    @SerializedName("organizer") val organizer: Organizer,
    @SerializedName("location") val location: Location? = null,
    @SerializedName("image") val image: String,
    @SerializedName("categories") val categories: List<Categories>
) : Parcelable