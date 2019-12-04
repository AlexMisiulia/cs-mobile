package com.binarysages.mobile.app.corespirit.ui.eventactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.events.Event
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_event.*

class EventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        val viewModel =
            ViewModelProviders.of(this).get(EventActivityViewModel::class.java)
        val event: Event = intent.getParcelableExtra("event")

        eventTypeTv.text = if (event.online) "online" else "offline"
        eventOrganizationNameTv.text = event.organizer.name
        eventTitleTv.text = event.title

        viewModel
            .loadEventById(event.id)
            .observe(this, Observer {
                it.data.event.tickets[0].cost.let { cost ->
                    eventPriceTv.text = R.string.event_cost.toString().plus(cost.value).plus(" ")
                        .plus(cost.currency)
                }
            })

        Glide.with(eventPageImageView.context)
            .load(event.image)
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(64)))
            .into(eventPageImageView)

        eventDescriptionTv.text = event.description
    }
}
