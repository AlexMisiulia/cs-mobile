package com.binarysages.mobile.app.corespirit.models.article


import android.os.Parcelable
import com.binarysages.mobile.app.corespirit.models.images.ImageModel
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Author(
    @SerializedName("avatarUrl")
    val avatarUrl: String?,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("credentialsRequired")
    val credentialsRequired: Boolean,
    @SerializedName("email")
    val email: String?,
    @SerializedName("emailVerified")
    val emailVerified: Boolean?,
    @SerializedName("fullName")
    val fullName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: ImageModel?,
    @SerializedName("imageId")
    val imageId: Int?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("verificationToken")
    val verificationToken: String?
) : Parcelable