package com.binarysages.mobile.app.corespirit.ui.homefragment


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binarysages.mobile.app.corespirit.models.articles.ArticlesModel
import com.binarysages.mobile.app.corespirit.network.NetworkServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private var articles: MutableLiveData<ArticlesModel>? = null

    private fun loadArticles(perPage: Int, offset: Int?) {
        NetworkServices
            .instance.getApiServices().getArticleApi()
            .getArticles(perPage = perPage, offset = offset)
            .enqueue(
                object : Callback<ArticlesModel> {
                    override fun onFailure(call: Call<ArticlesModel>, t: Throwable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(
                        call: Call<ArticlesModel>,
                        response: Response<ArticlesModel>
                    ) {
                        Log.d(">####", response.raw().toString())
                        articles?.postValue(response.body())
                    }
                }
            )
    }

    fun getArticles(perPage: Int = 3, offset: Int? = null): LiveData<ArticlesModel> {
        if (articles == null) {
            articles = MutableLiveData()
            loadArticles(perPage = perPage, offset = offset)
        }
        return this.articles!!
    }
}
