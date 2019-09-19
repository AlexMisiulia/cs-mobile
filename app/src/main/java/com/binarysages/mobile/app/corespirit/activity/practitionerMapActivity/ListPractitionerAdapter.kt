package com.binarysages.mobile.app.corespirit.activity.practitionerMapActivity

import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.PractitionerModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.net.URL


class ListPractitionerAdapter(val layout: ConstraintLayout) :
    RecyclerView.Adapter<ListPractitionerMapHolder>() {

    private var practitionerList: Array<PractitionerModel> =
        getRelatedPractitioners().execute(0).get()

    fun addPractitioner(count: Int = 0) {
        practitionerList += getRelatedPractitioners().execute(count).get()
    }

    private inner class getRelatedPractitioners :
        AsyncTask<Int, Void, Array<PractitionerModel>>() {
        override fun onPostExecute(result: Array<PractitionerModel>?) {
            super.onPostExecute(result)
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }

        private val baseURL: String = "https://corespirit.com/"

        override fun doInBackground(vararg params: Int?): Array<PractitionerModel> {
            return Gson().fromJson(
                Gson().fromJson(
                    URL(baseURL + "api/v1/practitioners/loadPractitioners?offset=${params[0]}").readText(),
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