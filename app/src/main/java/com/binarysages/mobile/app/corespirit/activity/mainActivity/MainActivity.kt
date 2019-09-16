package com.binarysages.mobile.app.corespirit.activity.mainActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.activity.BaseActivity
import com.binarysages.mobile.app.corespirit.activity.articleActivity.ArticleItemActivity
import com.binarysages.mobile.app.corespirit.activity.articleAdapter
import com.binarysages.mobile.app.corespirit.activity.isMainScreen
import com.binarysages.mobile.app.corespirit.activity.itemID
import com.binarysages.mobile.app.corespirit.models.ArticleModel
import com.binarysages.mobile.app.corespirit.network.CORE_SPIRIT_API

var articles: Array<ArticleModel>? = null

class MainActivity : BaseActivity() {
    override fun onLogoClick(view: View) {
        if (!isMainScreen) {
            super.onLogoClick(view)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(
            savedInstanceState,
            R.layout.activity_main
        )

//        check articles bundle. In empty - we come not from load screen
        intent.getBundleExtra("BUNDLE")?.let {
            articles = it.getSerializable("articles") as Array<ArticleModel>
        }
        val articlesRecyclerView: RecyclerView = findViewById(R.id.articlesRecycleViewList)
        val manager = LinearLayoutManager(this)
        articlesRecyclerView.layoutManager = manager

        //        set what listener must do
        val listener = object : MainActivityArticleListAdapter.OnArticleClickListener {
            override fun onArticleClick(articleModel: ArticleModel?) {
                val intent = Intent(this@MainActivity, ArticleItemActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable("articles", articleModel)
                intent.putExtra("BUNDLE", bundle)
                isMainScreen = false
                startActivity(intent)
            }
        }

        //        add listener to adapter
        articleAdapter =
            MainActivityArticleListAdapter(
                articles,
                listener
            )
        articlesRecyclerView.adapter = articleAdapter

//        add on scroll listener for infinity scroll
        articlesRecyclerView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (manager.itemCount - 3 == manager.findLastVisibleItemPosition()) {
                        if (itemID != null) {
                            CORE_SPIRIT_API.addArticles(
                                articleAdapter,
                                itemID
                            )
                        } else {
                            CORE_SPIRIT_API.addArticles(articleAdapter)
                        }
                    }
                }
            })
    }
}
