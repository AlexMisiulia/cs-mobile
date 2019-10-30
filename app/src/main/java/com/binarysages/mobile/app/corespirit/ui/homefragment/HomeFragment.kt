package com.binarysages.mobile.app.corespirit.ui.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.articles.ArticlesModel
import com.binarysages.mobile.app.corespirit.models.getURL
import com.binarysages.mobile.app.corespirit.network.NetworkServices
import com.binarysages.mobile.app.corespirit.recycleview.articleslist.ArticleAdapter
import com.bumptech.glide.Glide
import com.github.ybq.android.spinkit.SpinKitView
import kotlinx.android.synthetic.main.home_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private var container: ViewGroup? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.container = container
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        viewModel.getArticles(perPage = 1)
            .observe(viewLifecycleOwner, Observer {
                it.data.articles[0].let { article ->
                    Glide
                        .with(context!!)
                        .load(getURL(article.image!!))
                        .into(promoImageArticleHome)
                    promoTitleArticleHome.text = article.title
                }
            })

        viewModel
            .getArticles(perPage = 3, offset = 1)
            .observe(viewLifecycleOwner, Observer {
                recycle_view_articles.layoutManager = LinearLayoutManager(context)
                val adapter = ArticleAdapter(it.data.articles)
                recycle_view_articles.adapter = adapter

                loadMoreArticlesHomeBtn.setOnClickListener {
                    NetworkServices
                        .instance.getApiServices()
                        .getArticleApi()
                        .getArticles(perPage = 5)
                        .enqueue(object : Callback<ArticlesModel> {
                            override fun onFailure(call: Call<ArticlesModel>, t: Throwable) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                            override fun onResponse(
                                call: Call<ArticlesModel>,
                                response: Response<ArticlesModel>
                            ) {
                                adapter.addArticles(response.body()?.data?.articles!!)
                            }

                        })
                }

                spin_kit.visibility = SpinKitView.GONE
            })

    }
}
