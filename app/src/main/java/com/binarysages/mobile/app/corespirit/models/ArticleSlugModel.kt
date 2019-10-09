package com.binarysages.mobile.app.corespirit.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArticleSlugModel(
    @SerializedName("data") val data: Data
) : Serializable {
    data class Data(
        @SerializedName("article") val articles: ArticleModel
    ) : Serializable
}