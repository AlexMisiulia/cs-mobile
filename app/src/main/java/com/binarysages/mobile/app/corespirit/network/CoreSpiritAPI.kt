package com.binarysages.mobile.app.corespirit.network

import android.app.Activity
import android.os.AsyncTask
import android.widget.ProgressBar
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.adapters.ArticleListAdapter
import com.binarysages.mobile.app.corespirit.models.ArticleModel
import com.binarysages.mobile.app.corespirit.models.ArticleTree
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import java.net.URL

val CORE_SPIRIT_API: CoreSpiritAPI = CoreSpiritAPI()

class CoreSpiritAPI {

    private inner class GetArticleTreeByID(val onResult: (Array<ArticleModel>) -> Unit) :
        AsyncTask<Int, Void, Array<ArticleModel>>() {
        override fun onPostExecute(result: Array<ArticleModel>?) {
            onResult(result!!)
        }

        override fun doInBackground(vararg p0: Int?): Array<ArticleModel> {
            val s = p0[0].toString()
            val res =
                URL("https://master.stage.binarysages.com/api/Categories/$s/articles").readText()
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
            val res = URL("https://corespirit.com/api/v1/categories/tree").readText()
            return Gson().fromJson(res, ArticleTree::class.java)
        }
    }

    private inner class GetArticles(activity: Activity) :
        AsyncTask<Void, Void, Array<ArticleModel>>() {
        private val progressBar: ProgressBar = activity.findViewById(R.id.loadScreenProgressBar)
        override fun onPostExecute(result: Array<ArticleModel>?) {
            progressBar.visibility = ProgressBar.INVISIBLE
            super.onPostExecute(result)
        }

        override fun onPreExecute() {
            progressBar.visibility = ProgressBar.VISIBLE
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: Void?): Array<ArticleModel> {
//            In next iteration - move to env
            val res =
                URL("https://master.stage.binarysages.com/api/Articles/loadArticles?&skip=0").readText()
            Thread.sleep(10000)
            return GsonBuilder()
                .serializeNulls()
                .create()
                .fromJson<Array<ArticleModel>>(res, Array<ArticleModel>::class.java)
        }
    }

    fun getTree(): ArticleTree {
        return GetArticleTree().execute().get()
    }

    fun getArticlesOnLoad(
        activity: Activity
    ): Array<ArticleModel> {
        return GetArticles(activity).execute().get()
    }


    fun getArticles(categoryId: Int, adapter: ArticleListAdapter) {
        GetArticleTreeByID { result ->
            adapter.setArticles(result)
            adapter.notifyDataSetChanged()
        }.execute(categoryId)
    }
}