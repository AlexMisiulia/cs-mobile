package com.binarysages.mobile.app.corespirit.models.article


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @SerializedName("article")
    val articles: Article,

    @SerializedName("pageInfo")
    val pageInfo: PageInfo
) : Parcelable