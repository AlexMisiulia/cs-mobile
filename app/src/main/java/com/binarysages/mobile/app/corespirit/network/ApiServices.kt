package com.binarysages.mobile.app.corespirit.network

import com.binarysages.mobile.app.corespirit.network.api.ArticlesApi
import com.binarysages.mobile.app.corespirit.network.api.EventsApi
import com.binarysages.mobile.app.corespirit.network.api.PractitionersApi
import com.binarysages.mobile.app.corespirit.network.api.SearchApi
import retrofit2.Retrofit

class ApiServices(
    private val mRetrofit: Retrofit
) {
    fun getArticleApi(): ArticlesApi {
        return mRetrofit.create(ArticlesApi::class.java)
    }

    fun getPractitionersApi(): PractitionersApi {
        return mRetrofit.create(PractitionersApi::class.java)
    }

    fun getSearchApi(): SearchApi {
        return mRetrofit.create(SearchApi::class.java)
    }

    fun getEventsApi(): EventsApi {
        return mRetrofit.create(EventsApi::class.java)
    }
}