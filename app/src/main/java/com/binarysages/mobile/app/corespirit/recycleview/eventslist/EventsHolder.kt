package com.binarysages.mobile.app.corespirit.recycleview.eventslist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.events.Event
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class EventsHolder(itemView: View, val listener: (Event) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(event: Event) {
        itemView.setOnClickListener { listener(event) }
        Glide
            .with(itemView.context)
            .load(event.image)
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(16)))
            .into(itemView.findViewById(R.id.eventsListImage))
        itemView.findViewById<TextView>(R.id.eventsListTitle).text = event.title
    }
}