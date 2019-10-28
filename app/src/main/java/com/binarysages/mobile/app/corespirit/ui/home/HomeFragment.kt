package com.binarysages.mobile.app.corespirit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.ui.articleslist.ArticleAdapter
import com.github.ybq.android.spinkit.SpinKitView
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.getArticles().observe(viewLifecycleOwner, Observer {
            recycle_view_articles.layoutManager = LinearLayoutManager(context)
            recycle_view_articles.adapter = ArticleAdapter(it)
            spin_kit.visibility = SpinKitView.GONE
        })

    }
}
