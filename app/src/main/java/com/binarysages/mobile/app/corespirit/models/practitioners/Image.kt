package com.binarysages.mobile.app.corespirit.models.practitioners

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("originalName") val originalName: String,
    @SerializedName("description") val description: String,
    @SerializedName("name1200") val name1200: String,
    @SerializedName("name600x170") val name600x170: String,
    @SerializedName("name150x170") val name150x170: String,
    @SerializedName("type") val type: String,
    @SerializedName("size") val size: Int,
    @SerializedName("container") val container: String
) : Parcelable