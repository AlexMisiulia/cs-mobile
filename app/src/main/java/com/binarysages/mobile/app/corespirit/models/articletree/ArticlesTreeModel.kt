package com.binarysages.mobile.app.corespirit.models.articletree

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticlesTreeModel(
    @SerializedName("data") val data: CategoryTree?
) : Parcelable {

    @Parcelize
    data class CategoryTree(
        @SerializedName("categoryTree") val categoryTree: Array<Category>?
    ) : Parcelable {
        @Parcelize
        data class Category(
            @SerializedName("id") val id: Int,
            @SerializedName(" name") val name: String,
            @SerializedName("slug") val slug: String,
            @SerializedName("lang") val lang: String,
            @SerializedName("sorting") val sorting: String
        ) : Parcelable
    }

}