package com.binarysages.mobile.app.corespirit.models.events

import com.google.gson.annotations.SerializedName

data class EventsModel(
    @SerializedName("error") val error: String,
    @SerializedName("data") val data: Data
)