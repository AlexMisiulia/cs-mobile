package com.binarysages.mobile.app.corespirit.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://corespirit.com/api/v1/"
private const val BASE_URL_OLD = "https://corespirit.com/api/"

class NetworkService private constructor(
    private val url: String,
    private val mRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
) {
    companion object {
        fun getInstance(isOld: Boolean = false): NetworkService {
            return NetworkService(if (isOld) BASE_URL_OLD else BASE_URL)
        }
    }

    fun getJsonApi(): CorespiritApiWorker {
        return mRetrofit.create<CorespiritApiWorker>(CorespiritApiWorker::class.java)
    }

}
