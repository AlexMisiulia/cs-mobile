package com.binarysages.mobile.app.corespirit.network

import android.os.AsyncTask
import com.binarysages.mobile.app.corespirit.models.ArticleModel
import com.binarysages.mobile.app.corespirit.models.ArticleTree
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.coroutines.runBlocking
import java.net.URL

val apiWorks: ApiWorks = ApiWorks()

class ApiWorks {
    private inner class getArticleTreeID : AsyncTask<Int, Void, Array<ArticleModel>>() {
        override fun doInBackground(vararg p0: Int?): Array<ArticleModel> {
            val s = String.format(p0.get(0).toString())
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

    private inner class getArticleTree : AsyncTask<Void, Void, ArticleTree>() {
        override fun doInBackground(vararg p0: Void?): ArticleTree {
            val res = URL("https://corespirit.com/api/v1/categories/tree").readText()
            return Gson().fromJson(res, ArticleTree::class.java)
        }
    }

    private inner class Request : AsyncTask<Void, Void, Array<ArticleModel>>() {
        override fun doInBackground(vararg p0: Void?): Array<ArticleModel> {
//            In next iteration - move to env
            val res =
                URL("https://master.stage.binarysages.com/api/Articles/loadArticles?&skip=0").readText()

            return GsonBuilder()
                .serializeNulls()
                .create()
                .fromJson<Array<ArticleModel>>(res, Array<ArticleModel>::class.java)
        }
    }


    fun getTree(): ArticleTree = runBlocking {
        getArticleTree().execute().get()
    }

    fun getArticles(): Array<ArticleModel> = runBlocking {
        Request().execute().get()
    }

    fun getArticles(categoryId: Int): Array<ArticleModel> = runBlocking {
        getArticleTreeID().execute(categoryId).get()
    }
}