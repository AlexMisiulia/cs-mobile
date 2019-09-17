package com.binarysages.mobile.app.corespirit.activity.practitionerMapActivity

import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.PractitionerModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.net.URL


class ListPractitionerAdapter : RecyclerView.Adapter<ListPractitionerMapHolder>() {
    private var practitionerList: Array<PractitionerModel> =
        getRelatedPractitioners().execute().get()

    private inner class getRelatedPractitioners :
        AsyncTask<Void, Void, Array<PractitionerModel>>() {

        private val baseURL: String = "https://corespirit.com/"

        override fun doInBackground(vararg params: Void?): Array<PractitionerModel> {
            return Gson().fromJson(
                Gson().fromJson(
                    URL(baseURL + "api/v1/practitioners/loadPractitioners").readText(),
                    JsonObject::class.java
                ).getAsJsonArray("data"), Array<PractitionerModel>::class.java
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPractitionerMapHolder {
        val articleItemVIew: View =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.practitioner_map_item, parent, false)
        return ListPractitionerMapHolder(articleItemVIew)
    }

    override fun getItemCount(): Int {
        return practitionerList.size
    }

    override fun onBindViewHolder(holder: ListPractitionerMapHolder, position: Int) {
        holder.bind(practitionerList[position])
    }
}