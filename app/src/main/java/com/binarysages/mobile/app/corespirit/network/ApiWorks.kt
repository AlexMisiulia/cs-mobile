package com.binarysages.mobile.app.corespirit.network

import android.os.AsyncTask
import android.util.Log
import com.binarysages.mobile.app.corespirit.models.ArticleModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.net.URL


class ApiWorks {
    inner class Request : AsyncTask<Void,Void,ArrayList<ArticleModel>>() {
        override fun doInBackground(vararg p0: Void?): ArrayList<ArticleModel> {
            val res =  URL("https://master.stage.binarysages.com/api/Articles/loadArticles?&skip=0").readText()
            val  moshi =  Moshi
                .Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val type = Types.newParameterizedType(List::class.java, ArticleModel::class.java)
            val adapter: JsonAdapter<ArrayList<ArticleModel>> = moshi.adapter(type)
            return adapter.fromJson(res)!!
        }
    }
    fun getArticles(): ArrayList<ArticleModel> {
        val res = Request().execute().get()
        return res
    }
}