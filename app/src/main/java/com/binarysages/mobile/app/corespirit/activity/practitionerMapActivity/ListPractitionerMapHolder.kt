package com.binarysages.mobile.app.corespirit.activity.practitionerMapActivity

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.PractitionerModel
import com.binarysages.mobile.app.corespirit.network.getURL
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListPractitionerMapHolder(
    itemView: View,
    val clickListener: ListPractitionerAdapter.onPractitionerClickListener
) : RecyclerView.ViewHolder(itemView) {
    private val avatar: ImageView = itemView.findViewById(R.id.practitionerAvatar)
    private val name: TextView = itemView.findViewById(R.id.practitionerName)
    private val category: TextView = itemView.findViewById(R.id.practitionerCategory)


    fun bind(practitioner: PractitionerModel) {
        itemView.setOnClickListener {
            clickListener.practitionerCLick(practitioner)
        }

        Glide.with(itemView)
            .load(practitioner.image?.let { getURL(it) })
            .placeholder(R.mipmap.avatar_holder)
            .apply(RequestOptions.overrideOf(340, 340))
            .apply(RequestOptions.circleCropTransform())
            .into(avatar)

        name.text = practitioner.title
        val categoryBuilder = StringBuilder()
        practitioner.category?.forEach { StringBuilder().append(it.name).append(" ") }
        category.text = categoryBuilder.toString()
    }
}