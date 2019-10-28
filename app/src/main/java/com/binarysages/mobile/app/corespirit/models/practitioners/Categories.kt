package com.binarysages.mobile.app.corespirit.models.practitioners

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Categories(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("lang") val lang: String,
    @SerializedName("technical") val technical: Boolean,
    @SerializedName("sorting") val sorting: Int
) : Parcelable