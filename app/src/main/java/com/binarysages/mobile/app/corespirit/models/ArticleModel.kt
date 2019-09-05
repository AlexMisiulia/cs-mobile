package com.binarysages.mobile.app.corespirit.models

import com.squareup.moshi.Json

data class ArticleModel(
    @Json(name = "title")val articleTitle: String,
    @Json(name = "content")val articleContent: String,
    @Json(name = "authorId")val articleAuthor: String,
    @Json(name = "$.image.name")val articleURL: String

) {

    override fun toString(): String {
        return "ArticleModel(articleTitle='$articleTitle', articleContent='$articleContent')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ArticleModel

        if (articleTitle != other.articleTitle) return false
        if (articleContent != other.articleContent) return false

        return true
    }

    override fun hashCode(): Int {
        var result = articleTitle.hashCode()
        result = 31 * result + articleContent.hashCode()
        return result
    }

}