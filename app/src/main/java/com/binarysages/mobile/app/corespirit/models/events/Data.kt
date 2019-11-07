package com.binarysages.mobile.app.corespirit.models.events

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("events") val events: MutableList<Events>,
    @SerializedName("pageInfo") val pageInfo: PageInfo
)