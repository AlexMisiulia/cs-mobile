package com.binarysages.mobile.app.corespirit.models.article


import android.os.Parcelable
import com.binarysages.mobile.app.corespirit.models.images.ImageModel
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    @SerializedName("author")
    val author: Author?,
    @SerializedName("authorId")
    val authorId: Int?,
    @SerializedName("categories")
    val categories: List<Category>?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: ImageModel?,
    @SerializedName("imageId")
    val imageId: Int?,
    @SerializedName("preview")
    val preview: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("status")
    val status: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?
) : Parcelable