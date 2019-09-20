package com.binarysages.mobile.app.corespirit.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.activity.mainActivity.MainActivity
import com.binarysages.mobile.app.corespirit.activity.mainActivity.MainActivityArticleListAdapter
import com.binarysages.mobile.app.corespirit.menus.generateMenuFromTree
import com.binarysages.mobile.app.corespirit.menus.generateUserMenu
import com.binarysages.mobile.app.corespirit.network.CORE_SPIRIT_API

var itemID: Int? = null
var isMainScreen: Boolean = true

lateinit var articleAdapter: MainActivityArticleListAdapter

abstract class BaseActivity : AppCompatActivity() {
    open fun onLogoClick(view: View) {
        isMainScreen = true
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId in 1..2131165251) {
            isMainScreen = false
            CORE_SPIRIT_API.setArticles(
                articleAdapter,
                item.itemId,
                findViewById(R.id.LOAD_LAYOUT)
            )
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        generateMenuFromTree(menu)

        generateUserMenu(menu, null, this)
        return super.onCreateOptionsMenu(menu)
    }

    protected fun onCreate(savedInstanceState: Bundle?, layoutId: Int) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(
            R.anim.fadein,
            R.anim.fadeout
        )
        setContentView(layoutId)

//        Add toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbarTop)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}