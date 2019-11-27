package com.binarysages.mobile.app.corespirit.ui.practitionerfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binarysages.mobile.app.corespirit.models.practitioners.PractitionersModel
import com.binarysages.mobile.app.corespirit.network.NetworkServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PractitionerViewModel : ViewModel() {
    private var practitioners: MutableLiveData<PractitionersModel>? = null
    private var practitionerOffset = 0
    /*
    TODO: Make private
     */
    var isLoadComplete: MutableLiveData<Boolean> = MutableLiveData(false)


    fun isLoadComplete(): LiveData<Boolean> {
        return isLoadComplete
    }

    fun loadPractitioner() {
        NetworkServices
            .instance
            .getApiServices()
            .getPractitionersApi()
            .getPractitioners(offset = practitionerOffset)
            .enqueue(object : Callback<PractitionersModel> {
                override fun onFailure(call: Call<PractitionersModel>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(
                    call: Call<PractitionersModel>,
                    response: Response<PractitionersModel>
                ) {
                    practitioners?.postValue(response.body())
                    isLoadComplete.postValue(true)
                    practitionerOffset.plus(20)
                }
            })
    }

    fun getPractitioners(): LiveData<PractitionersModel> {
        if (practitioners == null) {
            practitioners = MutableLiveData()
            loadPractitioner()
        }
        return this.practitioners!!
    }
}
