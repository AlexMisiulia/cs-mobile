package com.binarysages.mobile.app.corespirit.network

import com.binarysages.mobile.app.corespirit.models.ArticleModel
import com.binarysages.mobile.app.corespirit.models.ArticlesModel
import com.binarysages.mobile.app.corespirit.models.PractitionersModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CorespiritApiWorker {
    @GET("articles")
    fun getArticle(@Query("categoryID") categoryID: Int? = null): Call<ArticlesModel>

    @GET("practitioners/loadPractitioners")
    fun getPractitioner(@Query("categoryIds") categoryIds: Int? = null): Call<PractitionersModel>

    @GET("practitioners/loadPractitioners")
    fun getPractitionersList(@Query("categoryIds") categoryIds: Int? = null): Call<PractitionersModel>

    @GET("articles/?category={id}")
    fun getArticlesWithID(@Path("id") id: Int?): Call<Array<ArticleModel>>
}