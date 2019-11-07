package com.binarysages.mobile.app.corespirit.recycleview.eventslist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.events.Events

class EventsAdapter : RecyclerView.Adapter<EventsHolder>() {
    private var events: MutableList<Events> = ArrayList()

    fun addEvents(events: MutableList<Events>) {
        this.events.addAll(events)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsHolder {
        val eventsListView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.events_list_view, parent, false)
        return EventsHolder(eventsListView, fun(event: Events) {
            Log.d(">####", event.toString())
        })
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: EventsHolder, position: Int) {
        holder.bind(events[position])
    }
}