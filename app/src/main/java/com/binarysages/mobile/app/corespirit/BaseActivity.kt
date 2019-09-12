package com.binarysages.mobile.app.corespirit

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.binarysages.mobile.app.corespirit.menus.generateMenuFromTree
import com.binarysages.mobile.app.corespirit.menus.generateUserMenu

abstract class BaseActivity : AppCompatActivity() {
    private var itemID: Int? = null
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        generateMenuFromTree(menu)
        generateUserMenu(menu, "guest", this)
        return super.onCreateOptionsMenu(menu)
    }

    protected fun onCreate(savedInstanceState: Bundle?, layoutId: Int) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

//        Add toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        assert(toolbar != null)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}