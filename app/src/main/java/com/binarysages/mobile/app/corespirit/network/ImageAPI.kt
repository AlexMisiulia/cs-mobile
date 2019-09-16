package com.binarysages.mobile.app.corespirit.network

import com.binarysages.mobile.app.corespirit.models.ArticleModel

fun getURL(articleModel: ArticleModel?, resolution: String = "1200"): String? {
    val baseURL = "https://corespirit.com/api/Containers/corespirit-static/download/"
    articleModel?.image?.let {
        articleModel.image.container?.let {
            articleModel.image.name1200?.let {
                return when (resolution) {
                    "1200" -> baseURL + articleModel.image.name1200
                    "600" -> baseURL + articleModel.image.name600
                    "150" -> baseURL + articleModel.image.name150
                    else -> baseURL + articleModel.image.name1200
                }
            }
            return baseURL + articleModel.image.imageName
        }
        return articleModel.image.legacyUrl
    }
    return null
}

