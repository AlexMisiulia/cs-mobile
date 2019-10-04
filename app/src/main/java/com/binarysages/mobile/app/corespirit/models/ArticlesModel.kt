package com.binarysages.mobile.app.corespirit.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ArticlesModel(
    @SerializedName("error") val error: String,
    @SerializedName("data") val data: Data
) : Serializable {
    inner class Data(
        @SerializedName("articles") val articles: Array<ArticleModel>?,
        @SerializedName("pageInfo") val pageInfo: PageInfo?
    ) : Serializable

    inner class PageInfo : Serializable
}
