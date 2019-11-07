package com.binarysages.mobile.app.corespirit.models.articles


import android.os.Parcelable
import com.binarysages.mobile.app.corespirit.models.article.Article
import com.binarysages.mobile.app.corespirit.models.article.PageInfo
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @SerializedName("articles")
    val articles: MutableList<Article> = mutableListOf(),

    @SerializedName("pageInfo")
    val pageInfo: PageInfo = PageInfo()
) : Parcelable