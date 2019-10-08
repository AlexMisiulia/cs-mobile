package com.binarysages.mobile.app.corespirit.network

import com.binarysages.mobile.app.corespirit.models.ArticleTree
import retrofit2.Call
import retrofit2.http.GET


interface CategoriesApi {
    @GET("categories/tree")
    fun getCategoriesTree(): Call<ArticleTree>

    @GET("categories/with_practitioners")
    fun getCategoriesWithPractitoners()
}