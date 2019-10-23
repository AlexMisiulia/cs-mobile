package com.binarysages.mobile.app.corespirit.network.api

import retrofit2.http.GET

interface CategoriesApi {
    @GET("categories/tree")
    fun getCategoriesTree()

    @GET("categories/with_practitioners")
    fun getCategoriesWithPractitoners()
}