package com.binarysages.mobile.app.corespirit.activity.staticPageActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.activity.BaseActivity
import com.binarysages.mobile.app.corespirit.activity.isMainScreen
import kotlinx.android.synthetic.main.activity_static_page.*

class StaticPageActivity : BaseActivity() {
    init {
        isMainScreen = false
    }

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState, R.layout.activity_static_page)
        when (intent?.getStringExtra("page")) {
            "about" -> {
                staticPageTitle.text = getString(R.string.about_us_title)
                staticPageContent.text = Html.fromHtml(getString(R.string.about_us_content))
            }
            "privacy" -> {
                staticPageTitle.text = getString(R.string.privacy_title)
                staticPageContent.text = Html.fromHtml(getString(R.string.privacy_content))
            }
            "terms" -> {
                staticPageTitle.text = getString(R.string.terms_title)
                staticPageContent.text = Html.fromHtml(getString(R.string.terms_content))
            }
        }
    }
}
