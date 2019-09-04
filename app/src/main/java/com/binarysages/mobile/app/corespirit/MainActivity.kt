package com.binarysages.mobile.app.corespirit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.adapters.ArticleListAdapter
import com.binarysages.mobile.app.corespirit.models.ArticleModel
import com.binarysages.mobile.app.corespirit.network.ApiWorks
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        find view by id
        val articlesList: RecyclerView = findViewById(R.id.ArticlesRecycleViewList)

//        set layout
        articlesList.layoutManager = LinearLayoutManager(this)

//        get articles list
        val articles: ArrayList<ArticleModel> = ApiWorks().getArticles()
//        val articles: ArrayList<ArticleModel> = getArticles()

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
        articlesList.adapter = articleAdapter
    }
}
