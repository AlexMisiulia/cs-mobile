package com.binarysages.mobile.app.corespirit.activity.mainActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.activity.BaseActivity
import com.binarysages.mobile.app.corespirit.activity.articleActivity.ArticleItemActivity
import com.binarysages.mobile.app.corespirit.activity.isMainScreen
import com.binarysages.mobile.app.corespirit.activity.itemId
import com.binarysages.mobile.app.corespirit.models.ArticleModel
import com.binarysages.mobile.app.corespirit.models.ArticlesModel
import com.binarysages.mobile.app.corespirit.network.CORE_SPIRIT_API
import com.binarysages.mobile.app.corespirit.network.NetworkService
import kotlinx.android.synthetic.main.load_more_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {
    private lateinit var articles: Array<ArticleModel>
    private lateinit var articleAdapter: MainActivityArticleListAdapter
    private var listener: MainActivityArticleListAdapter.OnArticleClickListener

    init {
        //        set what listener must do
        listener = object : MainActivityArticleListAdapter.OnArticleClickListener {
            override fun onArticleClick(articleModel: ArticleModel?) {
                val intent = Intent(this@MainActivity, ArticleItemActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable("articles", articleModel)
                intent.putExtra("BUNDLE", bundle)
                isMainScreen = false
                startActivity(intent)
            }
        }
    }

    private fun addArticles(articles: Array<ArticleModel>) {
        articleAdapter.addArticles(articles)
        articleAdapter.notifyDataSetChanged()
        LOAD_MORE_LAYOUT.visibility = ConstraintLayout.GONE
    }

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(
            savedInstanceState,
            R.layout.activity_main
        )

//        check articles bundle. In empty - we come not from load screen
        intent.getBundleExtra("BUNDLE")?.let {
            articles = it.getSerializable("articles") as Array<ArticleModel>
        } ?: run {
            articles = CORE_SPIRIT_API.getArticles()
        }

        articleAdapter =
            MainActivityArticleListAdapter(
                articles,
                listener
            )

        val articlesRecyclerView: RecyclerView = findViewById(R.id.articlesRecycleViewList)
        val manager = LinearLayoutManager(this)
        articlesRecyclerView.layoutManager = manager

        //        add listener to adapter
        articlesRecyclerView.adapter = articleAdapter

//        add on scroll listener for infinity scroll
        articlesRecyclerView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (manager.itemCount - 3 == manager.findLastVisibleItemPosition()) {
                        NetworkService
                            .getInstance()
                            .getJsonApi()
                            .getArticle(itemId)
                            .enqueue(object : Callback<ArticlesModel> {
                                override fun onFailure(
                                    call: Call<ArticlesModel>,
                                    t: Throwable
                                ) {
                                    call.cancel()
                                }

                                override fun onResponse(
                                    call: Call<ArticlesModel>,
                                    response: Response<ArticlesModel>
                                ) {
                                    response.body()?.data?.articles?.let {
                                        addArticles(it)
                                    }
                                }
                            }
                            )
                    }
                }
            }
        )
    }
}
