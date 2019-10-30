package com.binarysages.mobile.app.corespirit.recycleview.articleslist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.ArticleActivity
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.article.Article

class ArticleAdapter(
    private var articlesList: MutableList<Article>
) : RecyclerView.Adapter<ArticlesViewHolder>() {

    fun addArticles(articles: MutableList<Article>) {
        articlesList.addAll(articles)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val articleItemView: View =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.article_list_view, parent, false)

        return ArticlesViewHolder(articleItemView, fun(article: Article) {
            val intent = Intent(articleItemView.context, ArticleActivity::class.java)
            intent.putExtra("article", article)
            articleItemView.context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.bind(articlesList[position])
    }
}