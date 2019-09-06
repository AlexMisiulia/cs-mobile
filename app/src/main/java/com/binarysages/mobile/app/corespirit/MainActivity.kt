package com.binarysages.mobile.app.corespirit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.adapters.ArticleListAdapter
import com.binarysages.mobile.app.corespirit.models.ArticleModel
import com.binarysages.mobile.app.corespirit.network.ApiWorks


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        find view by id
        val articlesList: RecyclerView = findViewById(R.id.ArticlesRecycleViewList)
//        save manager
        val manager = LinearLayoutManager(this)
//        set layout
        articlesList.layoutManager = manager

//        get articles list
        var articles: Array<ArticleModel> = ApiWorks().getArticles()

//        set what listener must do
        val listener = object : ArticleListAdapter.OnArticleClickListener {
            override fun onArticleClick(articleModel: ArticleModel) {
                val intent = Intent(this@MainActivity, ArticleItemActivity::class.java)
                intent.putExtra("article.author", articleModel.articleAuthor)
                intent.putExtra("article.title", articleModel.articleTitle)
                intent.putExtra("article.content", articleModel.articleContent)
                startActivity(intent)
            }
        }

        //        add listener to adapter
        var articleAdapter = ArticleListAdapter(articles, listener)
        articlesList.adapter = articleAdapter

//        add on scroll listener for infinity scroll
        articlesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var isLoad = false
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visible = manager.findLastVisibleItemPosition()
                val total = manager.itemCount
                if ((total - 1) == visible) {
                    if (!isLoad) {
                        isLoad = true
                        articles = ApiWorks().getArticles()
                        articleAdapter = ArticleListAdapter(articles, listener)
                        articlesList.adapter = articleAdapter
                        isLoad = false
                    }
                }
            }
        })
    }
}
