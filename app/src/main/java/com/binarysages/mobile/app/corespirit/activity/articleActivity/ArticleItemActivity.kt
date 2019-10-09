package com.binarysages.mobile.app.corespirit.activity.articleActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.activity.BaseActivity
import com.binarysages.mobile.app.corespirit.models.ArticleModel
import com.binarysages.mobile.app.corespirit.models.ArticleSlugModel
import com.binarysages.mobile.app.corespirit.network.NetworkService
import com.binarysages.mobile.app.corespirit.network.getURL
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_card_view_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleItemActivity : BaseActivity() {
    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(
            savedInstanceState,
            R.layout.activity_card_view_item
        )

        intent?.getBundleExtra("BUNDLE")?.let {
            NetworkService
                .getInstance()
                .getJsonApi()
                .getArticleBySlug((it.getSerializable("articles") as ArticleModel).articleSlug)
                .enqueue(object : Callback<ArticleSlugModel> {
                    override fun onFailure(call: Call<ArticleSlugModel>, t: Throwable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(
                        call: Call<ArticleSlugModel>,
                        response: Response<ArticleSlugModel>
                    ) {
                        val articleModel = response.body()?.data?.articles
                        articleModel?.image?.let { img ->
                            getURL(img)?.let { imageURL ->
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
                        loadArticlesLayout.visibility = ConstraintLayout.GONE
                    }
                })
        }
    }
}
