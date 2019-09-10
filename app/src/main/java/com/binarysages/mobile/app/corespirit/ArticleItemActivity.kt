package com.binarysages.mobile.app.corespirit

import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ArticleItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_item)

        val author: TextView = findViewById(R.id.articleAuthorItem)
        val title: TextView = findViewById(R.id.articleTitleItem)
        val content: TextView = findViewById(R.id.articleContentItem)

        author.text = intent.getStringExtra("article.author")
        title.text = intent.getStringExtra("article.title")
        content.text = Html.fromHtml(intent.getStringExtra("article.content"))
    }
}
