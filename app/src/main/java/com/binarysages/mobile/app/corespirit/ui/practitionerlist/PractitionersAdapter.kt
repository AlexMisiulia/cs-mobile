package com.binarysages.mobile.app.corespirit.ui.practitionerlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.practitioners.PractitionersModel

class PractitionersAdapter(private val practitioner: PractitionersModel) :
    RecyclerView.Adapter<PractitionersHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PractitionersHolder {
        val practitionerItemView: View =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.practitioner_list_view, parent, false)

        return PractitionersHolder(practitionerItemView)
    }

    override fun getItemCount(): Int {
        return practitioner.data.size
    }

    override fun onBindViewHolder(holder: PractitionersHolder, position: Int) {
        holder.bind(practitioner.data[position])
    }
}