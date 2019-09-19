package com.binarysages.mobile.app.corespirit.network

import com.binarysages.mobile.app.corespirit.models.ImageModel

fun getURL(imageModel: ImageModel, resolution: String = "1200"): String? {
    val baseURL = "https://corespirit.com/api/Containers/corespirit-static/download/"
    imageModel.let {
        imageModel.container?.let {
            imageModel.name1200?.let {
                return when (resolution) {
                    "1200" -> baseURL + imageModel.name1200
                    "600" -> baseURL + imageModel.name600
                    "150" -> baseURL + imageModel.name150
                    else -> baseURL + imageModel.name1200
                }
            }
            return baseURL + imageModel.imageName
        }
        return imageModel.legacyUrl
    }
}

