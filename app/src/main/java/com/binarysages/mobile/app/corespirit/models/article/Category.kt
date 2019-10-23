package com.binarysages.mobile.app.corespirit.models.article


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("lang")
    val lang: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("slug")
    val slug: String = "",
    @SerializedName("sorting")
    val sorting: Int = 0,
    @SerializedName("technical")
    val technical: Boolean = false
) : Parcelable