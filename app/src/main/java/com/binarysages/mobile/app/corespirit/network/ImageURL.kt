package com.binarysages.mobile.app.corespirit.network

import com.binarysages.mobile.app.corespirit.models.ArticleModel

class ImageURL(val articleModel: ArticleModel) {
    fun getURL(): String {
        val baseURL = "https://master.stage.binarysages.com/api/Containers/corespirit-test/download/"
        articleModel.image?.let {
            articleModel.image.container?.let {
                articleModel.image.name1200?.let {
                    return baseURL + articleModel.image.name1200
                }
                return baseURL + articleModel.image.imageNAME
            }
            return articleModel.image.legacyUrl
        }
        return ""
    }
}
