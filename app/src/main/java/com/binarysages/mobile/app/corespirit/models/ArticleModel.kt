package com.binarysages.mobile.app.corespirit.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ArticleModel(
    @SerializedName(value = "title") val articleTitle: String?,
    @SerializedName(value = "content") val articleContent: String?,
    @SerializedName(value = "authorId") val articleAuthor: String?,
    @SerializedName(value = "image") val image: ImageModel?,
    @SerializedName(value = "preview") val preview: String
) : Serializable
