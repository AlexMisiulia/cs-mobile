package com.binarysages.mobile.app.corespirit.network.api

import com.binarysages.mobile.app.corespirit.models.eventitem.EventModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EventsApi {
    @GET("events/{eventID}/")
    fun getEventByID(
        @Path("eventID") eventID: Int
    ): Call<EventModel>
}