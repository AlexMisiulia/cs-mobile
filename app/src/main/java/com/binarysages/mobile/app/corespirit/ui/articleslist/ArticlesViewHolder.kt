package com.binarysages.mobile.app.corespirit.ui.articleslist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.article.Article
import com.binarysages.mobile.app.corespirit.models.getURL
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ArticlesViewHolder(itemView: View, val listener: (Article) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(article: Article) {
        itemView.setOnClickListener {
            listener(article)
        }

        article.image?.let {
            Glide
                .with(itemView)
                .load(getURL(it))
                .apply(RequestOptions.overrideOf(480, 480))
                .into(itemView.findViewById(R.id.article_list_image))

        }

        article.author?.fullName?.let {
            itemView.findViewById<TextView>(R.id.article_list_author).text =
                "By ".plus(article.author.fullName)
        }

        itemView.findViewById<TextView>(R.id.article_list_preview).text = article.preview
    }
}