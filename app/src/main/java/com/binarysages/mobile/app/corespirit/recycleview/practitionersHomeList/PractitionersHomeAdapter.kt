package com.binarysages.mobile.app.corespirit.recycleview.practitionersHomeList

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.PractitionerActivity
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.practitioners.Practitioner

class PractitionersHomeAdapter : RecyclerView.Adapter<PractitionersHomeHolder>() {
    private val practitioner: MutableList<Practitioner> = ArrayList()

    fun addPractitioners(practitioners: List<Practitioner>) {
        this.practitioner.addAll(practitioners)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PractitionersHomeHolder {
        val practitionerItemView: View =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.practitioner_list_view, parent, false)

        return PractitionersHomeHolder(practitionerItemView, fun(practitioner: Practitioner) {
            val intent = Intent(practitionerItemView.context, PractitionerActivity::class.java)
            intent.putExtra("practitioner", practitioner)
            practitionerItemView
                .context
                .startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return practitioner.size
    }

    override fun onBindViewHolder(holder: PractitionersHomeHolder, position: Int) {
        holder.bind(practitioner[position])
    }
}