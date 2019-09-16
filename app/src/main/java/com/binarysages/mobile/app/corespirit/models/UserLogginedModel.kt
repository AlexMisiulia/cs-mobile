package com.binarysages.mobile.app.corespirit.models

import com.google.gson.annotations.SerializedName

class UserLogginedModel(
    @SerializedName(value = "error") val error: Error,
    @SerializedName("id") val id: String,
    @SerializedName("userId") val userId: Int,
    @SerializedName("user") val user: User? = null,
    @SerializedName("practitioner") val practitioner: Practitioner? = null
) {
    data class Error(
        @SerializedName(value = "statusCode") val statusCode: Int
    )
    inner class Practitioner() {

    }

    inner class User() {

    }
}