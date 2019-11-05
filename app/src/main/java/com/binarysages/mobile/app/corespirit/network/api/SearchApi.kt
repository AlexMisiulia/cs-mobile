package com.binarysages.mobile.app.corespirit.network.api

import com.binarysages.mobile.app.corespirit.models.events.EventsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("search/events")
    fun getEvents(
        @Query("text") text: String? = null,
        @Query("organizer") organizer: String? = null,
        @Query("date") date: String? = null,
        @Query("lat") lat: Double? = null,
        @Query("lng") lng: Double? = null,
        @Query("dist") dist: Double? = null,
        @Query("format") format: Boolean? = null,
        @Query("type") type: Boolean? = null,
        @Query("cost") cost: Int? = null,
        @Query("genre") genre: String? = null,
        @Query("categoryId") categoryId: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("perPage") perPage: Int? = null
    ): Call<EventsModel>
}