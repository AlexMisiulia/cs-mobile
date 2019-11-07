package com.binarysages.mobile.app.corespirit.models.article


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    @SerializedName("legacyUrl")
    val legacyUrl: String?,
    @SerializedName("legacyPath")
    val legacyPath: String?,
    @SerializedName("legacyId")
    val legacyId: String?,
    @SerializedName("container")
    val container: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String?,
    @SerializedName("name1200")
    val name1200: String?,
    @SerializedName("name150x170")
    val name150x170: String?,
    @SerializedName("name600x170")
    val name600x170: String?,
    @SerializedName("originalName")
    val originalName: String?,
    @SerializedName("size")
    val size: Int = 0,
    @SerializedName("type")
    val type: String = ""
) : Parcelable