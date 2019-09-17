package com.binarysages.mobile.app.corespirit.activity.practitionerMapActivity

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.PractitionerModel
import com.bumptech.glide.Glide

class ListPractitionerMapHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val avatar: ImageView = itemView.findViewById(R.id.practitionerAvatar)
    private val name: TextView = itemView.findViewById(R.id.practitionerName)

    fun bind(practitioner: PractitionerModel){
//        Glide
//            .with(itemView)
//            .load(practitioner.imageid)
//            .into(avatar)
        name.text = practitioner.title
    }
}