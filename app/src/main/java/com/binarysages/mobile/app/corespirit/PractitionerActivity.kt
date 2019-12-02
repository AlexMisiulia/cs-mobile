package com.binarysages.mobile.app.corespirit

import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.binarysages.mobile.app.corespirit.models.getURL
import com.binarysages.mobile.app.corespirit.models.practitioners.Practitioner
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.practitioner_activity.*

class PractitionerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.practitioner_activity)
        val practitioner: Practitioner = intent.getParcelableExtra("practitioner")
        
        practitioner.title?.let {
            PractitionerPageName.text = it
        }

        practitioner.site?.let {
            PractitionerPageWebSite.text = it
            PractitionerPageWebSite.visibility = TextView.VISIBLE
        }

        practitioner.content?.let {
            PractitionerPageBIO.text =
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
                    Html.fromHtml(it, Html.FROM_HTML_MODE_LEGACY) else Html.fromHtml(it)
        }

        practitioner.image?.let {
            Glide
                .with(PractitionerPageAvatar.context)
                .load(getURL(it))
                .apply(RequestOptions.circleCropTransform())
                .into(PractitionerPageAvatar)
        }

        practitioner.categories.let {
            it.forEach {
                val cat = Chip(PractitionerPageCategories.context)
                cat.text = it.name
                cat.isClickable = false
                PractitionerPageCategories.addView(cat)
            }
        }
    }
}
