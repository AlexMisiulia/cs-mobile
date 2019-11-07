package com.binarysages.mobile.app.corespirit.models

import com.binarysages.mobile.app.corespirit.models.images.ImageModel

fun getURL(imageModel: ImageModel?, resolution: String = "1200"): String? {
    val baseURL = "https://corespirit.com/api/Containers/corespirit-static/download/"
    imageModel.let {
        imageModel?.container?.let {
            imageModel.name1200?.let {
                return when (resolution) {
                    "1200" -> baseURL + imageModel.name1200
                    "600" -> baseURL + imageModel.name600x170
                    "150" -> baseURL + imageModel.name150x170
                    else -> baseURL + imageModel.name1200
                }
            }
            return baseURL + imageModel.name
        }
        return imageModel?.legacyUrl
    }
}