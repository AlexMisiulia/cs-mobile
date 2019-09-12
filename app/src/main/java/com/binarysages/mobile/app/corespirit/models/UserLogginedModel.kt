package com.binarysages.mobile.app.corespirit.models

import com.google.gson.annotations.SerializedName

class UserLogginedModel (
    @SerializedName("id")private val id: String,
    @SerializedName("userId")private val userId: Int,
    @SerializedName("user")private val user: User?,
    @SerializedName("practitioner")private val practitioner: Practitioner?
) {
    inner class Practitioner() {

    }

    inner class User(){

    }
}