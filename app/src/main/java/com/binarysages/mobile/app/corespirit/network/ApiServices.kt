package com.binarysages.mobile.app.corespirit.network

import com.binarysages.mobile.app.corespirit.network.api.ArticlesApi
import retrofit2.Retrofit

class ApiServices(
    private val mRetrofit: Retrofit
) {
    fun getArticleApi(): ArticlesApi {
        return mRetrofit.create(ArticlesApi::class.java)
    }
}