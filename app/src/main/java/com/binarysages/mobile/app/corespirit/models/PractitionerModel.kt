package com.binarysages.mobile.app.corespirit.models

import com.google.gson.annotations.SerializedName

data class PractitionerModel(
    @SerializedName("id") val id: Int,
    @SerializedName("uri") val uri: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("image") val image: ImageModel?,
    @SerializedName("categories") val category: Array<Categories>?
) {
    inner class Categories(
        @SerializedName("name") val name: String?
    )
}