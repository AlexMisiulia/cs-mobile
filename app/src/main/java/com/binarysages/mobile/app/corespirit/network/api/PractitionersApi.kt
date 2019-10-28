package com.binarysages.mobile.app.corespirit.network.api

import com.binarysages.mobile.app.corespirit.models.practitioners.PractitionersModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PractitionersApi {
    @GET("practitioners/loadPractitioners")
    fun getPractitioners(
        @Query("categoryIds") categoryIds: String? = null,
        @Query("lat") lat: Int? = null,
        @Query("lng") lng: Int? = null,
        @Query("distance") distance: String? = null,
        @Query("offset") offset: Int? = null,
        @Query("perPage") perPage: Int? = null
    ): Call<PractitionersModel>
}