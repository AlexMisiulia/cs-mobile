package com.binarysages.mobile.app.corespirit.ui.eventsfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binarysages.mobile.app.corespirit.models.events.EventsModel
import com.binarysages.mobile.app.corespirit.network.NetworkServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val defaultOffset = 20

class EventsViewModel : ViewModel() {
    private var eventsList: MutableLiveData<EventsModel>? = null
    private var isLoadComplete: MutableLiveData<Boolean> = MutableLiveData(true)
    private var eventsOffset = 0

    fun isLoadCompete(): LiveData<Boolean>{
        return isLoadComplete
    }

    fun loadEvents() {
        isLoadComplete.postValue(false)
        NetworkServices
            .instance.getApiServices().getSearchApi()
            .getEvents(offset = eventsOffset)
            .enqueue(
                object : Callback<EventsModel> {
                    override fun onFailure(call: Call<EventsModel>, t: Throwable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(
                        call: Call<EventsModel>,
                        response: Response<EventsModel>
                    ) {
                        eventsList?.postValue(response.body())
                        isLoadComplete.postValue(true)
                        eventsOffset += defaultOffset
                    }
                })

    }

    fun getEventsList(): LiveData<EventsModel> {
        if (eventsList == null) {
            eventsList = MutableLiveData()
            loadEvents()
        }
        return eventsList!!
    }
}
