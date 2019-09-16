package com.binarysages.mobile.app.corespirit

import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import com.binarysages.mobile.app.corespirit.models.ArticleModel
import com.binarysages.mobile.app.corespirit.network.getURL
import com.bumptech.glide.Glide

class ArticleItemActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState, R.layout.activity_card_view_item)

        val articleModel: ArticleModel? = intent?.getBundleExtra("BUNDLE")?.let {
            it.getSerializable("articles") as ArticleModel
        }
        val author: TextView = findViewById(R.id.articleAuthorItem)
        val title: TextView = findViewById(R.id.articleTitleItem)
        val content: TextView = findViewById(R.id.articleContentItem)
        val image: ImageView = findViewById(R.id.articleImg)
        Glide.with(content)
            .load(getURL(articleModel))
            .thumbnail(Glide.with(content).load(R.drawable.tenor))
            .centerCrop()
            .fitCenter()
            .into(image)

        author.text = articleModel?.articleAuthor
        title.text = articleModel?.articleTitle
        content.text = Html.fromHtml(articleModel?.articleContent)
    }
}
