package com.binarysages.mobile.app.corespirit.ui.practitionerlist

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.practitioners.Practitioner

class PractitionersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(practitioner: Practitioner) {
        itemView.findViewById<TextView>(R.id.practitionerListName).text = practitioner.title
        itemView.findViewById<ImageView>(R.id.practitionerListAvatar)
        Log.d(">>>>", itemView.findViewById<TextView>(R.id.practitionerListName).text.toString())
    }
}