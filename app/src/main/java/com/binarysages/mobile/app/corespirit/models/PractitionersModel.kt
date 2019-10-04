package com.binarysages.mobile.app.corespirit.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PractitionersModel(
    @SerializedName("error") val error: String,
    @SerializedName("data") val data: Array<PractitionerModel>?
) : Serializable