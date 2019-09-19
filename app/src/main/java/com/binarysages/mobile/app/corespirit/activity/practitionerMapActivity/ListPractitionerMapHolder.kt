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

class ListPractitionerMapHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val avatar: ImageView = itemView.findViewById(R.id.practitionerAvatar)
    private val name: TextView = itemView.findViewById(R.id.practitionerName)
    private val catogory: TextView = itemView.findViewById(R.id.practitionerCategory)

    fun bind(practitioner: PractitionerModel) {
        practitioner.image?.let {
            getURL(it)?.let { imgURL ->
                Glide
                    .with(itemView)
                    .load(imgURL)
                    .thumbnail(
                        Glide.with(itemView).load(R.drawable.tenor).apply(
                            RequestOptions.overrideOf(
                                400,
                                400
                            )
                        )
                    )
                    .apply(RequestOptions.overrideOf(400, 400))
                    .apply(RequestOptions.circleCropTransform())
                    .into(avatar)
            }
        }
        name.text = practitioner.title
        val categoryBuilder = StringBuilder()
        practitioner.category?.forEach { categoryBuilder.append(it.name).append(" ") }
        catogory.text = categoryBuilder.toString()
    }
}