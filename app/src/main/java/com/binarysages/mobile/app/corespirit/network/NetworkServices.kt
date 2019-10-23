package com.binarysages.mobile.app.corespirit.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkServices private constructor() {
    private val mRetrofit: Retrofit

    init {
        mRetrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        private var mInstance: NetworkServices? = null
        private val baseUrl = "https://corespirit.com/api/v1/"

        val instance: NetworkServices
            get() {
                if (mInstance == null) {
                    mInstance = NetworkServices()
                }
                return mInstance!!
            }
    }

    fun getApiServices(): ApiServices {
        return ApiServices(mRetrofit)
    }
}