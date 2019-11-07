package com.binarysages.mobile.app.corespirit.ui.homefragment


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binarysages.mobile.app.corespirit.models.article.Article
import com.binarysages.mobile.app.corespirit.models.articles.ArticlesModel
import com.binarysages.mobile.app.corespirit.models.events.EventsModel
import com.binarysages.mobile.app.corespirit.models.practitioners.PractitionersModel
import com.binarysages.mobile.app.corespirit.network.NetworkServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private var articles: MutableLiveData<ArticlesModel>? = null
    private var events: MutableLiveData<EventsModel>? = null
    private var promo: MutableLiveData<Article>? = null
    private var practitioners: MutableLiveData<PractitionersModel>? = null
    private var isLoadComplete: MutableLiveData<Boolean> = MutableLiveData(false)
    private var offsetArticles = 0
    private var offsetEvents = 0
    private var offsetPractitioners = 0
    private var counter = 0

    fun isLoadComplete(): LiveData<Boolean> {
        return isLoadComplete
    }

    private fun loadCompleteIncrement() {
        if (counter == 2) isLoadComplete.postValue(true)
        else {
            counter++
        }
    }

    fun loadPractitioners(perPage: Int? = 5, offset: Int? = this.offsetPractitioners) {
        NetworkServices
            .instance.getApiServices()
            .getPractitionersApi()
            .getPractitioners(perPage = perPage, offset = offset)
            .enqueue(object : Callback<PractitionersModel> {
                override fun onResponse(
                    call: Call<PractitionersModel>,
                    response: Response<PractitionersModel>
                ) {
                    practitioners?.postValue(response.body())
                    loadCompleteIncrement()
                }

                override fun onFailure(call: Call<PractitionersModel>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
    }

    fun loadEvents(perPage: Int? = 5, offset: Int? = this.offsetEvents) {
        NetworkServices
            .instance.getApiServices().getSearchApi()
            .getEvents(
                offset = offset,
                perPage = perPage
            )
            .enqueue(object : Callback<EventsModel> {
                override fun onFailure(call: Call<EventsModel>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<EventsModel>, response: Response<EventsModel>) {
                    response.body()?.let {
                        events?.postValue(it)
                        loadCompleteIncrement()
                        this@HomeViewModel.offsetEvents += 5
                    }
                }
            })
    }

    fun loadArticles(perPage: Int, offset: Int? = null) {
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
                        promo?.postValue(response.body()?.data!!.articles[0])
                        this@HomeViewModel.offsetArticles += 5
                        articles?.postValue(response.body())
                        loadCompleteIncrement()
                    }
                }
            )
    }

    fun getEvents(perPage: Int? = 3, offset: Int? = null): LiveData<EventsModel> {
        if (events == null) {
            events = MutableLiveData()
            loadEvents(perPage = perPage, offset = offset)
        }
        return events!!
    }

    fun getArticles(perPage: Int = 3, offset: Int? = null): LiveData<ArticlesModel> {
        if (articles == null) {
            articles = MutableLiveData()
            loadArticles(perPage = perPage, offset = offset)
        }
        return this.articles!!
    }

    fun getPromo(): LiveData<Article> {
        if (promo == null) {
            promo = MutableLiveData()
        }
        return this.promo!!
    }

    fun getPractitioners(): LiveData<PractitionersModel> {
        if (practitioners == null) {
            practitioners = MutableLiveData()
            loadPractitioners()
        }
        return this.practitioners!!
    }
}
