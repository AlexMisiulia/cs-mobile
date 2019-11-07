package com.binarysages.mobile.app.corespirit.models.events

import com.google.gson.annotations.SerializedName

data class Organizer(

    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String
)