package com.binarysages.mobile.app.corespirit

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.menus.generateMenuFromTree
import com.binarysages.mobile.app.corespirit.menus.generateUserMenu
import com.binarysages.mobile.app.corespirit.adapters.ArticleListAdapter
import com.binarysages.mobile.app.corespirit.models.ArticleModel
import com.binarysages.mobile.app.corespirit.network.CORE_SPIRIT_API


class MainActivity : AppCompatActivity() {
    private lateinit var articleAdapter: ArticleListAdapter
    private var itemID: Int? = null

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId in 1..2131165251) {
            CORE_SPIRIT_API.getArticles(item.itemId, articleAdapter)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        generateMenuFromTree(menu)
        generateUserMenu(menu,"guest", this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle: Bundle = intent.getBundleExtra("BUNDLE")!!
        val articles: Array<ArticleModel> =
            bundle.getSerializable("articles") as Array<ArticleModel>

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //        find view by id
        val articlesRecyclerView: RecyclerView = findViewById(R.id.articlesRecycleViewList)
        //        save manager
        val manager = LinearLayoutManager(this)
//        set layout
        articlesRecyclerView.layoutManager = manager
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
        articleAdapter = ArticleListAdapter(articles, listener)
        articlesRecyclerView.adapter = articleAdapter

//        add on scroll listener for infinity scroll
        articlesRecyclerView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (manager.itemCount - 3 == manager.findLastVisibleItemPosition()) {
                        if (itemID != null) {
                            CORE_SPIRIT_API.getArticles(itemID!!, articleAdapter)
                        } else {
                            CORE_SPIRIT_API.getArticles(articleAdapter)
                        }
                    }
                }
            })
    }
}
