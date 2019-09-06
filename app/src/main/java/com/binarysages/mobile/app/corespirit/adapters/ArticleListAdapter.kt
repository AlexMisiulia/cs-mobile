package com.binarysages.mobile.app.corespirit.adapters

import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.ArticleModel
import com.binarysages.mobile.app.corespirit.network.getURL
import com.squareup.picasso.Picasso


class ArticleListAdapter(
    internal var articlesListArray: Array<ArticleModel>,
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
        return articlesListArray.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articlesListArray[position])
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                articleClickListener.onArticleClick(articlesListArray[layoutPosition])
            }
        }

        private val image: ImageView = itemView.findViewById(R.id.articleImage)
        private val title: TextView = itemView.findViewById(R.id.articleTitle)
        private val content: TextView = itemView.findViewById(R.id.articleContent)
        private val author: TextView = itemView.findViewById(R.id.articleAuthor)


        fun bind(articleModel: ArticleModel) {
            if (getURL(articleModel) != "") {
                Log.d("IMAGE", getURL(articleModel))
                Picasso.get()
                    .load(getURL(articleModel))
                    .placeholder(R.drawable.img_148071)
                    .into(image)
            }
            title.text = articleModel.articleTitle
            content.text = Html.fromHtml(articleModel.articleContent)
            author.text = articleModel.articleAuthor
        }
    }
}