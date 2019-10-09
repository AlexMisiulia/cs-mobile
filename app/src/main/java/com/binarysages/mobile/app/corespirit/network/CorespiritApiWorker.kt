package com.binarysages.mobile.app.corespirit.network

import com.binarysages.mobile.app.corespirit.models.ArticleSlugModel
import com.binarysages.mobile.app.corespirit.models.ArticlesModel
import com.binarysages.mobile.app.corespirit.models.ArticlesModelOld
import com.binarysages.mobile.app.corespirit.models.PractitionersModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CorespiritApiWorker {
    @GET("articles")
    fun getArticles(
        @Query("categoryID") categoryID: Int? = null,
        @Query("offset") offset: Int? = null
    ): Call<ArticlesModel>

    @GET("practitioners/loadPractitioners")
    fun getPractitioner(
        @Query("categoryIds") categoryIds: Int? = null,
        @Query("offset") offset: Int? = null
    ): Call<PractitionersModel>

    @GET("articles/{slug}")
    fun getArticleBySlug(
        @Path("slug") slug: String
    ): Call<ArticleSlugModel>

    @GET("Categories/{id}/articles")
    fun getArticlesOldApi(
        @Path("id") id: Int?,
        @Query("offset") offset: Int? = null,
        @Query("categoryId") categoryId: Int? = null
    ): Call<ArticlesModelOld>

    @GET("Articles/loadArticles")
    fun getArticlesOldApi(): Call<Array<ArticlesModel>>
}