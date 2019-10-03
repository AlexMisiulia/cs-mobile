package com.binarysages.mobile.app.corespirit.network

import com.binarysages.mobile.app.corespirit.models.ArticleModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CorespiritApiWorker {
    fun getArticle(categoryID: Int?): Call<Array<ArticleModel>> {
        categoryID?.let {
            return getArticlesWithID(it)
        } ?: run {
            return getArticlesWithOutID()
        }
    }

    @GET("/Categories/{id}/articles")
    fun getArticlesWithID(@Path("id") id: Int): Call<Array<ArticleModel>>

    @GET("/Articles/loadArticles")
    fun getArticlesWithOutID(): Call<Array<ArticleModel>>
}