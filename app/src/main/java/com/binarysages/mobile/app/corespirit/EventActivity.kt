package com.binarysages.mobile.app.corespirit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.binarysages.mobile.app.corespirit.models.eventitem.EventModel
import com.binarysages.mobile.app.corespirit.models.events.Event
import com.binarysages.mobile.app.corespirit.network.NetworkServices
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_event.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        val event: Event = intent.getParcelableExtra("event")
        eventType.text = if (event.online) "online" else "offline"
        eventOrganizationName.text = event.organizer.name
        eventTitle.text = event.title

        NetworkServices
            .instance.getApiServices().getEventsAPi()
            .getEventByID(event.id).enqueue(object : Callback<EventModel> {
                override fun onFailure(call: Call<EventModel>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<EventModel>, response: Response<EventModel>) {
                    eventPrice.text = response.body()?.data?.event?.tickets?.get(0)?.cost?.currency
                }
            })

        Glide.with(eventPageImage.context)
            .load(event.image)
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(64)))
            .into(eventPageImage)

        eventDescription.text = event.description
    }
}
