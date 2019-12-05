package com.binarysages.mobile.app.corespirit.models.events

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PageInfo(
    @SerializedName("total") val total: Double,
    @SerializedName("totalPages") val totalPages: Double,
    @SerializedName("page") val page: Double,
    @SerializedName("perPage") val perPage: Double,
    @SerializedName("nextPage") val nextPage: Double,
    @SerializedName("prevPage") val prevPage: Double,
    @SerializedName("hasNext") val hasNext: Boolean,
    @SerializedName("hasPrev") val hasPrev: Boolean
) : Parcelable