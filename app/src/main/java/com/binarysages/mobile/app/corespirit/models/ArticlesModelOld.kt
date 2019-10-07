package com.binarysages.mobile.app.corespirit.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ArticlesModelOld(
    @SerializedName("articles") val articles: Array<ArticleModel>?
) : Serializable
