package com.binarysages.mobile.app.corespirit.ui.home


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

    private fun loadArticles() {
        NetworkServices
            .instance.getApiServices().getArticleApi()
            .getArticles().enqueue(
                object : Callback<ArticlesModel> {
                    override fun onFailure(call: Call<ArticlesModel>, t: Throwable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(
                        call: Call<ArticlesModel>,
                        response: Response<ArticlesModel>
                    ) {
                        articles?.postValue(response.body())
                    }
                }
            )
    }

    fun getArticles(): LiveData<ArticlesModel> {
        if (articles == null) {
            articles = MutableLiveData()
            loadArticles()
        }
        return this.articles!!
    }
}
