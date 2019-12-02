package com.binarysages.mobile.app.corespirit.recycleview.eventslist

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.EventActivity
import com.binarysages.mobile.app.corespirit.PractitionerActivity
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.events.Event

class EventsAdapter : RecyclerView.Adapter<EventsHolder>() {
    private var events: MutableList<Event> = ArrayList()

    fun addEvents(events: MutableList<Event>) {
        this.events.addAll(events)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsHolder {
        val eventsListView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.events_list_view, parent, false)

        return EventsHolder(eventsListView, fun(event: Event) {
            val intent = Intent(eventsListView.context, EventActivity::class.java)
            intent.putExtra("event", event)
            eventsListView.context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: EventsHolder, position: Int) {
        holder.bind(events[position])
    }
}