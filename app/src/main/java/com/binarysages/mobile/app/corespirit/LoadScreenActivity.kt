package com.binarysages.mobile.app.corespirit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.binarysages.mobile.app.corespirit.network.CORE_SPIRIT_API

class LoadScreenActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        CORE_SPIRIT_API.getArticlesOnLoad()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_screen_activty)
    }
}
