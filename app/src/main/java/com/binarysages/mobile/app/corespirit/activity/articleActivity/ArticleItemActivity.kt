package com.binarysages.mobile.app.corespirit.activity.articleActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.ImageView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.activity.BaseActivity
import com.binarysages.mobile.app.corespirit.models.ArticleModel
import com.binarysages.mobile.app.corespirit.network.getURL
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_card_view_item.*

class ArticleItemActivity : BaseActivity() {
    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(
            savedInstanceState,
            R.layout.activity_card_view_item
        )

        val articleModel: ArticleModel? = intent?.getBundleExtra("BUNDLE")?.let {
            it.getSerializable("articles") as ArticleModel
        }

        articleModel?.image?.let {
            getURL(it)?.let { imageURL ->
                Glide.with(articleContentItem)
                    .load(imageURL)
                    .thumbnail(Glide.with(articleContentItem).load(R.mipmap.tenor))
                    .centerCrop()
                    .fitCenter()
                    .into(articleImg)
                articleImg.visibility = ImageView.VISIBLE
            }
        }

        articleAuthorItem.text = articleModel?.articleAuthor
        articleTitleItem.text = articleModel?.articleTitle
        articleContentItem.text = Html.fromHtml(articleModel?.articleContent)
        articleContentItem.movementMethod = LinkMovementMethod.getInstance()
    }
}
