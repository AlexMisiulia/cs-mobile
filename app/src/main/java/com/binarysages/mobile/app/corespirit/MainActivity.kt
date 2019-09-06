package com.binarysages.mobile.app.corespirit

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.adapters.ArticleListAdapter
import com.binarysages.mobile.app.corespirit.models.ArticleModel
import com.binarysages.mobile.app.corespirit.network.apiWorks


class MainActivity : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        find view by id
        val articlesRecyclerView: RecyclerView = findViewById(R.id.articlesRecycleViewList)
//        save manager
        val manager = LinearLayoutManager(this)
//        set layout
        articlesRecyclerView.layoutManager = manager

//        get articles list
        var articles: Array<ArticleModel> = apiWorks.getArticles()

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
        val articleAdapter = ArticleListAdapter(articles, listener)
        articlesRecyclerView.adapter = articleAdapter

//        add on scroll listener for infinity scroll
        articlesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var isLoad = false
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visible = manager.findLastVisibleItemPosition()
                val total = manager.itemCount
                if ((total - 1) == visible) {
                    if (!isLoad) {
                        isLoad = true
                        articles = apiWorks.getArticles()
                        articleAdapter.articlesListArray
                        isLoad = false
                    }
                }
            }
        })
    }
}
