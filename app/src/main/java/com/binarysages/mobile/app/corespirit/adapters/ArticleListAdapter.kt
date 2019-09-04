package com.binarysages.mobile.app.corespirit.adapters

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.models.ArticleModel
import com.binarysages.mobile.app.corespirit.R


class ArticleListAdapter(
    private val articlesList: ArrayList<ArticleModel>,
    private val articleClickListener: OnArticleClickListener
) : RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder>() {

//    Interface listener
    interface OnArticleClickListener {
        fun onArticleClick(articleModel: ArticleModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val articleItemVIew: View =
            LayoutInflater.from(parent.context).inflate(R.layout.article_list_view, parent, false)
        return ArticleViewHolder(articleItemVIew)
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articlesList[position])
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                articleClickListener.onArticleClick(articlesList[layoutPosition])
            }
        }

        private val title: TextView = itemView.findViewById(R.id.articleTitle)
        private val content: TextView = itemView.findViewById(R.id.articleContent)
        private val author: TextView = itemView.findViewById(R.id.articleAuthor)


        fun bind(articleModel: ArticleModel) {
            title.text = articleModel.articleTitle
            content.text = Html.fromHtml(articleModel.articleContent)
            author.text = articleModel.articleAuthor
        }
    }
}