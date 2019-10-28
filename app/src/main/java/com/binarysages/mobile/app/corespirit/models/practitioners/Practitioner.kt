package com.binarysages.mobile.app.corespirit.models.practitioners

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Practitioner(
    @SerializedName("id") val id: Int,
    @SerializedName("uri") val uri: String,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("site") val site: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("email") val email: String,
    @SerializedName("imageid") val imageid: Int,
    @SerializedName("location") val location: String,
    @SerializedName("published") val published: Boolean,
    @SerializedName("paidservices") val paidservices: String,
    @SerializedName("coreuserid") val coreuserid: Int,
    @SerializedName("locationpoint") val locationpoint: LocationPoint?,
    @SerializedName("name") val name: String,
    @SerializedName("surname") val surname: String,
    @SerializedName("image") val image: Image,
    @SerializedName("categories") val categories: List<Categories>
) : Parcelable