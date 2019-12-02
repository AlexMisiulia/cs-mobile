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
            PractitionerPageNameTv.text = it
        }

        practitioner.site?.let {
            PractitionerPageWebSiteTv.text = it
            PractitionerPageWebSiteTv.visibility = TextView.VISIBLE
        }

        practitioner.content?.let {
            PractitionerPageBIOTv.text =
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
                    Html.fromHtml(it, Html.FROM_HTML_MODE_LEGACY) else Html.fromHtml(it)
        }

        practitioner.image?.let {
            Glide
                .with(PractitionerPageAvatarIv.context)
                .load(getURL(it))
                .apply(RequestOptions.circleCropTransform())
                .into(PractitionerPageAvatarIv)
        }

        practitioner.categories.let {
            it.forEach {
                val cat = Chip(PractitionerPageCategoriesChipGroup.context)
                cat.text = it.name
                cat.isClickable = false
                PractitionerPageCategoriesChipGroup.addView(cat)
            }
        }
    }
}
