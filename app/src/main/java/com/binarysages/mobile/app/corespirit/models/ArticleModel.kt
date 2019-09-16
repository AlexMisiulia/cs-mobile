package com.binarysages.mobile.app.corespirit.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ArticleModel(
    @SerializedName(value = "title") val articleTitle: String?,
    @SerializedName(value = "content") val articleContent: String?,
    @SerializedName(value = "authorId") val articleAuthor: String?,
    @SerializedName(value = "image") val image: ArticleItem?,
    @SerializedName(value = "preview") val preview: String
) : Serializable {
    data class ArticleItem(
        @SerializedName(value = "container") val container: String?,
        @SerializedName(value = "name") internal val imageName: String,
        @SerializedName(value = "legacyUrl") val legacyUrl: String,
        @SerializedName(value = "name1200") val name1200: String?,
        @SerializedName(value = "name600x170") val name600: String?,
        @SerializedName(value = "name150x170") val name150: String?
    ) : Serializable
}
