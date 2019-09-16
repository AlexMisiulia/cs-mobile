package com.binarysages.mobile.app.corespirit.network

import android.os.AsyncTask
import androidx.constraintlayout.widget.ConstraintLayout
import com.binarysages.mobile.app.corespirit.activity.mainActivity.MainActivityArticleListAdapter
import com.binarysages.mobile.app.corespirit.models.ArticleModel
import com.binarysages.mobile.app.corespirit.models.ArticleTree
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import java.net.URL

val CORE_SPIRIT_API: CoreSpiritAPI = CoreSpiritAPI()

class CoreSpiritAPI {
    private val baseURL: String = "https://corespirit.com/api"
    private lateinit var layoutProgressBar: ConstraintLayout

    private inner class GetArticleByID(val onResult: (Array<ArticleModel>) -> Unit) :
        AsyncTask<Int, Void, Array<ArticleModel>>() {
        override fun onPostExecute(result: Array<ArticleModel>?) {
            if (result != null) {
                onResult(result)
            }
            layoutProgressBar.visibility = ConstraintLayout.GONE
        }

        override fun onPreExecute() {
            layoutProgressBar.visibility = ConstraintLayout.VISIBLE
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: Int?): Array<ArticleModel> {
            val s = p0[0].toString()
            val res =
                URL("$baseURL/Categories/$s/articles").readText()
            return Gson().fromJson(
                Gson().fromJson(
                    res,
                    JsonObject::class.java
                ).getAsJsonArray("articles"), Array<ArticleModel>::class.java
            )
        }
    }

    private inner class GetArticleTree : AsyncTask<Void, Void, ArticleTree>() {
        override fun doInBackground(vararg p0: Void?): ArticleTree {
            val res = URL("$baseURL/v1/categories/tree").readText()
            return Gson().fromJson(res, ArticleTree::class.java)
        }
    }

    private inner class GetArticles(val onResult: (Array<ArticleModel>) -> Unit) :
        AsyncTask<Void, Void, Array<ArticleModel>>() {
        override fun onPostExecute(result: Array<ArticleModel>?) {
            if (result != null) {
                onResult(result)
            }
            super.onPostExecute(result)
        }

        override fun doInBackground(vararg p0: Void?): Array<ArticleModel> {
//            In next iteration - move to env
            val res =
                URL("$baseURL/Articles/loadArticles").readText()
            return GsonBuilder()
                .serializeNulls()
                .create()
                .fromJson<Array<ArticleModel>>(res, Array<ArticleModel>::class.java)
        }
    }

    //    get category tree
    fun getArticleTree(): ArticleTree {
        return GetArticleTree().execute().get()
    }

    //    add articles to exist list
    fun addArticles(adapter: MainActivityArticleListAdapter, categoryId: Int? = null) {
        categoryId?.let {
            GetArticleByID { result ->
                adapter.addArticles(result)
            }.execute(it)
        } ?: kotlin.run {
            GetArticles { result ->
                adapter.addArticles(result)
            }.execute()
        }
    }

    //    Reload article adapter with new categories
    fun setArticles(
        adapter: MainActivityArticleListAdapter,
        categoryId: Int? = null,
        linearLayout: ConstraintLayout
    ) {
        this.layoutProgressBar = linearLayout
        categoryId?.let {
            GetArticleByID { result ->
                adapter.setArticles(result)
            }.execute(it).get()
        } ?: run {
            GetArticles { result ->
                adapter.setArticles(result)
            }.execute().get()
        }
    }
}