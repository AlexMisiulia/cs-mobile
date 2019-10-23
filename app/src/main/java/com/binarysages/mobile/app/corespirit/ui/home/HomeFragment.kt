package com.binarysages.mobile.app.corespirit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.articles.ArticlesModel
import com.binarysages.mobile.app.corespirit.network.NetworkServices
import com.binarysages.mobile.app.corespirit.ui.articleslist.ArticleAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        NetworkServices
            .instance
            .getApiServices()
            .getArticleApi()
            .getArticles()
            .enqueue(object : Callback<ArticlesModel> {
                override fun onFailure(call: Call<ArticlesModel>, t: Throwable) {
                    call.cancel()
                }

                override fun onResponse(
                    call: Call<ArticlesModel>,
                    response: Response<ArticlesModel>
                ) {
                    val mRecycleView =
                        container?.findViewById<RecyclerView>(R.id.recycle_view_articles)
                    mRecycleView?.let {
                        container.findViewById<ProgressBar>(R.id.main_fragment_progress_bar)
                            .visibility = ProgressBar.GONE
                        it.layoutManager = LinearLayoutManager(context)
                        it.adapter = ArticleAdapter(response.body()!!)
                    }
                }
            })
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }
}
