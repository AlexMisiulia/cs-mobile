package com.binarysages.mobile.app.corespirit.recycleview.practitionerlist

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.PractitionerActivity
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.practitioners.Practitioner
import com.binarysages.mobile.app.corespirit.models.practitioners.PractitionersModel

class PractitionersAdapter(private val practitioner: PractitionersModel) :
    RecyclerView.Adapter<PractitionersHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PractitionersHolder {
        val practitionerItemView: View =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.practitioner_list_view, parent, false)

        return PractitionersHolder(practitionerItemView, fun(practitioner: Practitioner) {
            Log.d("####", "init click")
            val intent = Intent(practitionerItemView.context, PractitionerActivity::class.java)
            intent.putExtra("practitioner", practitioner)
            practitionerItemView
                .context
                .startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return practitioner.data.size
    }

    override fun onBindViewHolder(holder: PractitionersHolder, position: Int) {
        holder.bind(practitioner.data[position])
    }
}