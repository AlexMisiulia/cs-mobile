package com.binarysages.mobile.app.corespirit.activity.practitionerProfileActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.activity.BaseActivity
import com.binarysages.mobile.app.corespirit.models.ImageModel
import com.binarysages.mobile.app.corespirit.network.getURL
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class PractitionerInfoActivity : BaseActivity() {
    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState, R.layout.practitioner_profile_activity)
        intent.let {
            it.getStringExtra("userName")?.let { str ->
                val userName = findViewById<TextView>(R.id.userNamePractitionerProfile)
                userName.text = str
            }
            it.getBundleExtra("imgURL")?.let { bundle ->
                bundle.getSerializable("image")?.let { img ->
                    val avatar = findViewById<ImageView>(R.id.userAvatarPractitionerProfile)
                    Glide
                        .with(this)
                        .load(getURL(img as ImageModel))
                        .circleCrop()
                        .apply(RequestOptions.overrideOf(400, 400))
                        .into(avatar)
                }
            }
            it.getStringExtra("bio")?.let { str ->
                val bio = findViewById<TextView>(R.id.userBioPractitionerProfile)
                bio.text = Html.fromHtml(str)
                bio.visibility = TextView.VISIBLE
            }
            it.getStringExtra("phone")?.let { str ->
                findViewById<TextView>(R.id.userNamePractitionerPhone).text = str
            }
            it.getStringExtra("webSite")?.let { str ->
                val item = findViewById<TextView>(R.id.userNamePractitionerWedSite)
                item.text = Html.fromHtml(str)
                item.visibility = TextView.VISIBLE
                item.movementMethod = LinkMovementMethod.getInstance()
            }
            it.getStringExtra("address")?.let { str ->
                val item = findViewById<TextView>(R.id.userNamePractitionerAddress)
                item.text = str
                item.visibility = TextView.VISIBLE
            }
        }
    }
}