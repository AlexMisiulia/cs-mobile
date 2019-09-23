package com.binarysages.mobile.app.corespirit.activity.practitionerProfileActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.activity.BaseActivity
import com.bumptech.glide.Glide

class PractitionerInfoActivity(
    private var userName: String? = "Name hidden",
    private var bio: String? = null,
    private var category: String? = null,
    private var imgURL: String? = null
) : BaseActivity() {

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState, R.layout.practitioner_profile_activity)
        savedInstanceState?.let {
            Log.d(">>>>> D", it.get("userName").toString())
            it.get("userName")?.let { name -> userName = name.toString() }
            it.get("bio")?.let { sBio -> bio = sBio.toString() }
            it.getString("category")?.let { sCategory -> category = sCategory }
            it.getString("imgURL")?.let { sImgURL -> imgURL = sImgURL }
        }

        val mUserName: TextView = findViewById(R.id.userNamePractitionerProfile)
        bio?.let {
            findViewById<TextView>(R.id.bioPractitionerProfile).text = bio
        }
        category?.let {
            findViewById<TextView>(R.id.categoryPractitionerProfile).text = category
        }
        imgURL?.let {
            Glide.with(this)
                .load(imgURL)
                .placeholder(R.mipmap.avatar_holder)
                .into(findViewById(R.id.avatarPractitionerProfile))
        }
    }
}