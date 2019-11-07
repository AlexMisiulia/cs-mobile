package com.binarysages.mobile.app.corespirit.models.articles


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticlesModel(
    @SerializedName("data")
    val data: Data,

    @SerializedName("error")
    val error: String
) : Parcelable