package com.binarysages.mobile.app.corespirit.models

import com.google.gson.annotations.SerializedName


data class ArticleModel(
    @SerializedName(value = "title") val articleTitle: String,
    @SerializedName(value = "content") val articleContent: String,
    @SerializedName(value = "authorId") val articleAuthor: String,
    @SerializedName(value = "image") val image: ArticleItem?
) {
    data class ArticleItem(
        @SerializedName(value = "container") val container: String?,
        @SerializedName(value = "name") val imageNAME: String,
        @SerializedName(value = "legacyUrl") val legacyUrl: String,
        @SerializedName(value = "name1200") val name1200: String?
    )
}