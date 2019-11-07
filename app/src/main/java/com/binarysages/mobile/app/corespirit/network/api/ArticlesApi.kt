package com.binarysages.mobile.app.corespirit.network.api

import com.binarysages.mobile.app.corespirit.models.article.ArticleModel
import com.binarysages.mobile.app.corespirit.models.articles.ArticlesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticlesApi {
    @GET("articles")
    fun getArticles(
        @Query("page") page: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("perPage") perPage: Int? = null,
        @Query("directionId") directionId: Int? = null,
        @Query("categoryId") categoryId: Int? = null,
        @Query("authorId") authorId: Int? = null
    ): Call<ArticlesModel>

    @GET("articles/{slug}/")
    fun getArticleBySlug(@Path("slug") slug: String): Call<ArticleModel>
}