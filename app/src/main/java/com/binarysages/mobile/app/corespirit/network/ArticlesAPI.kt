package com.binarysages.mobile.app.corespirit.network

import android.os.AsyncTask
import com.binarysages.mobile.app.corespirit.models.ArticleTree
import com.google.gson.Gson
import java.net.URL

val CORE_SPIRIT_API: CoreSpiritAPI = CoreSpiritAPI()

class CoreSpiritAPI {
    private val baseURL: String = "https://corespirit.com/api"

    private inner class GetArticleTree : AsyncTask<Void, Void, ArticleTree>() {
        override fun doInBackground(vararg p0: Void?): ArticleTree {
            val res = URL("$baseURL/v1/categories/tree").readText()
            return Gson().fromJson(res, ArticleTree::class.java)
        }
    }

    //    get category tree
    fun getArticleTree(): ArticleTree {
        return GetArticleTree().execute().get()
    }

}