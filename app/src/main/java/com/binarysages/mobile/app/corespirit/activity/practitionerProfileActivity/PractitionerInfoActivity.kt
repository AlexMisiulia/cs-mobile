package com.binarysages.mobile.app.corespirit.activity.practitionerProfileActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.util.Log
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
        val userName = findViewById<TextView>(R.id.userNamePractitionerProfile)
        val bio = findViewById<TextView>(R.id.userBioPractitionerProfile)
        val avatar = findViewById<ImageView>(R.id.userAvatarPractitionerProfile)
        intent.let {
            it.getStringExtra("userName")?.let { str ->
                userName.text = str
            }

            it.getBundleExtra("imgURL")?.let { bundle ->
                bundle.getSerializable("image")?.let { img ->
                    Glide
                        .with(this)
                        .load(getURL(img as ImageModel))
                        .circleCrop()
                        .apply(RequestOptions.overrideOf(400, 400))
                        .into(avatar)
                }
            }
            Log.d(">>> BIO", it.getStringExtra("bio").toString())
            it.getStringExtra("bio")?.let { str ->
                bio.text = Html.fromHtml(str)
                bio.visibility = TextView.VISIBLE
            }?: run {
                bio.visibility = TextView.GONE
            }
        }
    }
}