package com.binarysages.mobile.app.corespirit.network

import android.os.AsyncTask
import com.binarysages.mobile.app.corespirit.models.ArticleModel
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import java.net.URL

val apiWorks: ApiWorks = ApiWorks()

class ApiWorks {
    inner class Request : AsyncTask<Void, Void, Array<ArticleModel>>() {
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

    fun getArticles(): Array<ArticleModel> = runBlocking {
        Request().execute().get()
    }
}