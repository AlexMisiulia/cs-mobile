package com.binarysages.mobile.app.corespirit.ui.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.getURL
import com.binarysages.mobile.app.corespirit.recycleview.articleslist.ArticleAdapter
import com.binarysages.mobile.app.corespirit.recycleview.eventslist.EventsAdapter
import com.bumptech.glide.Glide
import com.github.ybq.android.spinkit.SpinKitView
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private var container: ViewGroup? = null
    private val articleAdapter = ArticleAdapter()
    private val eventsAdapter = EventsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.container = container
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        return view
    }

    private fun initView() {
        recycle_view_articles.adapter = articleAdapter
        recycle_view_articles.layoutManager = LinearLayoutManager(context)
        recycle_view_articles
            .addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (recyclerView.layoutManager?.itemCount == (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() + 1) {
                        loadMoreArticlesHomeBtn.visibility = MaterialButton.VISIBLE
                    } else {
                        loadMoreArticlesHomeBtn.visibility = MaterialButton.GONE
                    }
                }
            })

        recycleViewEvents.adapter = eventsAdapter
        recycleViewEvents.layoutManager = LinearLayoutManager(context)

        loadMoreArticlesHomeBtn.setOnClickListener {
            viewModel
                .loadArticles(perPage = 5)
        }

        loadMoreEventsHomeBtn.setOnClickListener {
            viewModel
                .loadEvents(perPage = 5)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        initView()

//        spinkit
        viewModel
            .isLoadComplete().observe(viewLifecycleOwner, Observer {
                if (it) spin_kit.visibility = SpinKitView.GONE
                else {
                    spin_kit.visibility = SpinKitView.VISIBLE
                }
            })

//        promo
        viewModel.getPromo()
            .observe(viewLifecycleOwner, Observer {
                Glide
                    .with(context!!)
                    .load(getURL(it.image!!))
                    .into(promoImageArticleHome)
                promoTitleArticleHome.text = it.title
            })

//        events
        viewModel
            .getEvents()
            .observe(viewLifecycleOwner, Observer {
                eventsAdapter.addEvents(it.data.events)
            })

//        articles
        viewModel
            .getArticles()
            .observe(viewLifecycleOwner, Observer {
                articleAdapter.addArticles(it.data.articles)
            })
    }
}
