package com.binarysages.mobile.app.corespirit.models.practitioners

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PractitionersModel(
    @SerializedName("error") val error: String,
    @SerializedName("data") val data: List<Practitioner>
) : Parcelable