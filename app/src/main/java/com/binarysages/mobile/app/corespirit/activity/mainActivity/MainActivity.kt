package com.binarysages.mobile.app.corespirit.activity.mainActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.binarysages.mobile.app.corespirit.models.ArticlesModelOld
import com.binarysages.mobile.app.corespirit.network.NetworkService
import kotlinx.android.synthetic.main.load_activity_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {
    private lateinit var articles: Array<ArticleModel>
    private var articleAdapter: MainActivityArticleListAdapter
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

        articleAdapter =
            MainActivityArticleListAdapter(
                articleClickListener = listener
            )
    }

    private fun addArticles(articles: Array<ArticleModel>) {
        articleAdapter.addArticles(articles)
        articleAdapter.notifyDataSetChanged()
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
            articleAdapter.setArticles(articles)
            LOAD_LAYOUT.visibility = ConstraintLayout.GONE
        } ?: run {
            itemId?.let {
                NetworkService
                    .getInstance(true)
                    .getJsonApi()
                    .getArticlesOldApi(itemId)
                    .enqueue(object : Callback<ArticlesModelOld> {
                        override fun onFailure(call: Call<ArticlesModelOld>, t: Throwable) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }

                        override fun onResponse(
                            call: Call<ArticlesModelOld>,
                            response: Response<ArticlesModelOld>
                        ) {
                            articleAdapter.addArticles(response.body()?.articles!!)
                            LOAD_LAYOUT.visibility = ConstraintLayout.GONE
                        }
                    })
            } ?: run {
                NetworkService
                    .getInstance(false)
                    .getJsonApi()
                    .getArticles()
                    .enqueue(object : Callback<ArticlesModel> {
                        override fun onFailure(call: Call<ArticlesModel>, t: Throwable) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }

                        override fun onResponse(
                            call: Call<ArticlesModel>,
                            response: Response<ArticlesModel>
                        ) {
                            Log.d(">>>>>>#", response.message())
                            Log.d(">>>>>>#", response.raw().toString())
                            Log.d(">>>>>>#", response.body().toString())
                            articleAdapter.addArticles(response.body()?.data?.articles!!)
                            LOAD_LAYOUT.visibility = ConstraintLayout.GONE
                        }
                    })
            }
        }

        val articlesRecyclerView: RecyclerView = findViewById(R.id.articlesRecycleViewList)
        val manager = LinearLayoutManager(this)
        articlesRecyclerView.layoutManager = manager

        //        add listener to adapter
        articlesRecyclerView.adapter = articleAdapter

//        add on scroll listener for infinity scroll
        var offset = 10
        var canLoad = true
        articlesRecyclerView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if ((manager.itemCount - 3 == manager.findLastVisibleItemPosition()) && canLoad) {
                        canLoad = false
                        itemId?.let {
                            NetworkService
                                .getInstance(true)
                                .getJsonApi()
                                .getArticlesOldApi(it, offset, it)
                                .enqueue(object : Callback<ArticlesModelOld> {
                                    override fun onFailure(
                                        call: Call<ArticlesModelOld>,
                                        t: Throwable
                                    ) {
                                        call.cancel()
                                    }

                                    override fun onResponse(
                                        call: Call<ArticlesModelOld>,
                                        response: Response<ArticlesModelOld>
                                    ) {
                                        addArticles(response.body()?.articles!!)
                                        articleAdapter.notifyDataSetChanged()
                                        offset += 10
                                        canLoad = true
                                    }
                                })
                        } ?: run {
                            NetworkService
                                .getInstance(false)
                                .getJsonApi()
                                .getArticles(offset = offset)
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
                                        addArticles(response.body()?.data?.articles!!)
                                        offset += 10
                                        canLoad = true
                                    }
                                })

                        }
                    }
                }
            }
        )
    }
}
