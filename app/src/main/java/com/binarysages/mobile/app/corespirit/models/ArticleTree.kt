package com.binarysages.mobile.app.corespirit.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArticleTree(
    @SerializedName("data") val data: Data
) : Serializable {
    data class Data(
        @SerializedName("categoryTree") val categoryTree: Array<CategoryTree>
    ) : Serializable {
        data class CategoryTree(
            @SerializedName("hasArticles") val hasArticles: Boolean,
            @SerializedName("id") val id: String,
            @SerializedName("name") val name: String,
            @SerializedName("categories") val categories: Array<CategoryTree>?
        ) : Serializable
    }
}