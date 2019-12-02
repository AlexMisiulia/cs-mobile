package com.binarysages.mobile.app.corespirit

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.binarysages.mobile.app.corespirit.models.article.Article
import com.binarysages.mobile.app.corespirit.models.article.ArticleModel
import com.binarysages.mobile.app.corespirit.network.NetworkServices
import kotlinx.android.synthetic.main.article_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article_activity)

        val article = intent.getParcelableExtra<Article>("article")
        NetworkServices
            .instance.getApiServices()
            .getArticleApi().getArticleBySlug(article.slug)
            .enqueue(object : Callback<ArticleModel> {
                override fun onFailure(call: Call<ArticleModel>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(
                    call: Call<ArticleModel>,
                    response: Response<ArticleModel>
                ) {
                    response.body()?.let {
                        articleProgressBar.visibility = ProgressBar.GONE
                        article_item_title.text = it.data.articles.title
                        article_item_author.text = "By ".plus(it.data.articles.author?.fullName)
                        article_item_content.setHtml(it.data.articles.content.toString())
                    }
                }
            })
    }

}
