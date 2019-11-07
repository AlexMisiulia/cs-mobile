package com.binarysages.mobile.app.corespirit.models.events

import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("lang") val lang: String,
    @SerializedName("technical") val technical: Boolean,
    @SerializedName("sorting") val sorting: Int
)