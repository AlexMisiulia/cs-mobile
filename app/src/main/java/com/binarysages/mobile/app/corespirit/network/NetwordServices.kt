package com.binarysages.mobile.app.corespirit.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://corespirit.com/api/v1/"
private var mInstance: NetworkService? = null

class NetworkService private constructor(
    private val mRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
) {
    companion object {
        fun getInstance(): NetworkService {
            if (mInstance == null) {
                mInstance = NetworkService()
            }
            return mInstance!!
        }
    }

    fun getJsonApi(): CorespiritApiWorker {
        return mRetrofit.create<CorespiritApiWorker>(CorespiritApiWorker::class.java)
    }

}
