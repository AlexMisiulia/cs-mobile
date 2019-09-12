package com.binarysages.mobile.app.corespirit.network

import com.binarysages.mobile.app.corespirit.models.ArticleModel

fun getURL(articleModel: ArticleModel): String {
    val baseURL = "https://corespirit.com/api/Containers/corespirit-test/download/"
    articleModel.image?.let {
        articleModel.image.container?.let {
            articleModel.image.name1200?.let {
                return baseURL + articleModel.image.name1200
            }
            return baseURL + articleModel.image.imageName
        }
        return articleModel.image.legacyUrl
    }
    return ""
}

