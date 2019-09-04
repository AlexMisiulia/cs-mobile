package com.binarysages.mobile.app.corespirit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.adapters.ArticleListAdapter
import com.binarysages.mobile.app.corespirit.models.ArticleModel
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
//        get articles lilst
        val articles: ArrayList<ArticleModel> = getArticles()
//        set what listener must do
        val listener = object : ArticleListAdapter.OnArticleClickListener {
            override fun onArticleClick(articleModel: ArticleModel) {
                val intent: Intent = Intent(this@MainActivity, ArticleItemActivity::class.java)
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

    //    mock articles list
    private fun getArticles(): ArrayList<ArticleModel> {
        return ArrayList(
            Arrays.asList(
                ArticleModel("Title2", "Content2", "Author2"),
                ArticleModel("Title3", "Content3", "Author3"),
                ArticleModel("Title4", "Content4", "Author4"),
                ArticleModel("Title5", "Content5", "Author5"),
                ArticleModel("Title5", "Content5", "Author5"),
                ArticleModel("Title5", "Content5", "Author5"),
                ArticleModel("Title5", "Content5", "Author5"),
                ArticleModel("Title5", "Content5", "Author5"),
                ArticleModel("Title5", "Content5", "Author5"),
                ArticleModel("Title5", "Content5", "Author5"),
                ArticleModel("Title5", "Content5", "Author5"),
                ArticleModel("Title5", "Content5", "Author5"),
                ArticleModel("Title5", "Content5", "Author5"),
                ArticleModel("Title5", "Content5", "Author5"),
                ArticleModel("Title5", "Content5", "Author5"),
                ArticleModel("Title5", "Content5", "Author5"),
                ArticleModel("Title5", "Content5", "Author5"),
                ArticleModel("Title5", "Content5", "Author5"),
                ArticleModel("Title5", "Content5", "Author5"),
                ArticleModel("Title5", "Content5", "Author5"),
                ArticleModel("Title5", "Content5", "Author5"),
                ArticleModel("Title5", "Content5", "Author5"),
                ArticleModel("Title5", "Content5", "Author5"),
                ArticleModel("Title5", "Content5", "Author5"),
                ArticleModel("Title6", "Content6", "Author6")
            )
        )
    }
}
