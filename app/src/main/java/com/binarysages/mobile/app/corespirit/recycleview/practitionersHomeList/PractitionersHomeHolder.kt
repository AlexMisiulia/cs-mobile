package com.binarysages.mobile.app.corespirit.recycleview.practitionersHomeList

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.getURL
import com.binarysages.mobile.app.corespirit.models.practitioners.Practitioner
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class PractitionersHomeHolder(itemView: View, val clickListener: (Practitioner) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(practitioner: Practitioner) {
        itemView.setOnClickListener {
            clickListener(practitioner)
        }

        itemView.findViewById<TextView>(R.id.practitionerListName).text = practitioner.title
        val categories = itemView.findViewById<ChipGroup>(R.id.practitionerListCategories)
        var count = 0
        for (i in practitioner.categories) {
            if (count == 2) break
            val cat = Chip(itemView.context)
            cat.text = i.name
            cat.isClickable = false
            categories.addView(cat)
            count++
        }

        Glide
            .with(itemView.context)
            .load(getURL(practitioner.image))
            .placeholder(R.drawable.missing_avatar)
            .apply(RequestOptions.circleCropTransform())
            .into(itemView.findViewById(R.id.practitionerListAvatar))
    }
}