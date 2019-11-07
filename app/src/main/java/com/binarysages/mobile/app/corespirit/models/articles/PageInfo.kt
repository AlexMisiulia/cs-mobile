package com.binarysages.mobile.app.corespirit.models.articles


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PageInfo(
    @SerializedName("hasNext")
    val hasNext: Boolean = false,
    @SerializedName("hasPrev")
    val hasPrev: Boolean = false,
    @SerializedName("nextPage")
    val nextPage: Int = 0,
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("perPage")
    val perPage: Int = 0,
    @SerializedName("prevPage")
    val prevPage: Int = 0,
    @SerializedName("total")
    val total: Int = 0,
    @SerializedName("totalPages")
    val totalPages: Int = 0
) : Parcelable