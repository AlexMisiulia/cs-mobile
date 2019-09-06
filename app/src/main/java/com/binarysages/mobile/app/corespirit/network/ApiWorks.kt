package com.binarysages.mobile.app.corespirit.network

import android.os.AsyncTask
import com.binarysages.mobile.app.corespirit.models.ArticleModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.net.URL


class ApiWorks {
    inner class Request : AsyncTask<Void, Void, Array<ArticleModel>>() {
        override fun doInBackground(vararg p0: Void?): Array<ArticleModel> {
            val res =
                URL("https://master.stage.binarysages.com/api/Articles/loadArticles?&skip=0").readText()
            val gson = GsonBuilder().serializeNulls().create()
            val list: Array<ArticleModel> =  gson.fromJson<Array<ArticleModel>>(res, Array<ArticleModel>::class.java)
            return list
        }
    }

    fun getArticles(): Array<ArticleModel> {
        val res = Request().execute().get()
        return res
    }
}