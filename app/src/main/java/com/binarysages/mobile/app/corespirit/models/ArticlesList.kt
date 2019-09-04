package com.binarysages.mobile.app.corespirit.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ArticlesList(
    articles: ArrayList<ArticleModel>
) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }
}