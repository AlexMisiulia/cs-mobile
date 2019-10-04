package com.binarysages.mobile.app.corespirit.network

import android.os.AsyncTask
import androidx.constraintlayout.widget.ConstraintLayout
import com.binarysages.mobile.app.corespirit.models.ArticleModel
import com.binarysages.mobile.app.corespirit.models.ArticleTree
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

        override fun doInBackground(vararg p0: Int?): Array<ArticleModel>? {
            var articles: Array<ArticleModel>? = null
            NetworkService
                .getInstance()
                .getJsonApi()
                .getArticlesWithID(p0[0]!!.toInt())
                .enqueue(object : Callback<Array<ArticleModel>> {
                    override fun onFailure(call: Call<Array<ArticleModel>>, t: Throwable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(
                        call: Call<Array<ArticleModel>>,
                        response: Response<Array<ArticleModel>>
                    ) {
                        articles = response.body()!!
                    }
                })
            return articles
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

    fun getArticles(categoryId: Int? = null): Array<ArticleModel> {
        categoryId?.let {
            return GetArticleByID { it }.execute().get()
        } ?: run {
            return GetArticles {
            }.execute().get()
        }
    }

}