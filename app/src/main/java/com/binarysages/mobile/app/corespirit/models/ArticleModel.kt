package com.binarysages.mobile.app.corespirit.models

class ArticleModel(
    val articleTitle: String,
    val articleContent: String,
    val articleAuthor: String
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