package com.binarysages.mobile.app.corespirit.ui.eventactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binarysages.mobile.app.corespirit.models.eventitem.EventModel
import com.binarysages.mobile.app.corespirit.network.NetworkServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventActivityViewModel : ViewModel() {
    private val event: MutableLiveData<EventModel> = MutableLiveData()

    fun loadEventById(eventID: Int): LiveData<EventModel> {
        NetworkServices
            .instance.getApiServices().getEventsApi()
            .getEventByID(eventID)
            .enqueue(object : Callback<EventModel> {
                override fun onFailure(call: Call<EventModel>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<EventModel>, response: Response<EventModel>) {
                    event.postValue(response.body())
                }
            })
        return event
    }
}
