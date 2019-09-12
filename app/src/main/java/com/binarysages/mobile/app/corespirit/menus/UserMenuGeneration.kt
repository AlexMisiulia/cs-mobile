package com.binarysages.mobile.app.corespirit.menus

import android.app.Activity
import android.content.Intent
import android.view.Menu
import com.binarysages.mobile.app.corespirit.LoginActivity
import com.binarysages.mobile.app.corespirit.R

fun generateUserMenu(menu: Menu?, role: String, activity: Activity) {
    val userMenu = menu?.findItem(R.id.userMenu)
//    sub menu
    val userMenuLogIn = userMenu?.subMenu?.findItem(R.id.userMenuLogIn)
    userMenuLogIn?.setOnMenuItemClickListener {
        val intent = Intent(activity, LoginActivity::class.java)
        activity.startActivity(intent)
        return@setOnMenuItemClickListener true
    }

    val userMenuLogOut = userMenu?.subMenu?.findItem(R.id.userMenuLogOut)
    userMenuLogOut?.setOnMenuItemClickListener {
        menu.clear()
        generateUserMenu(menu, "guest", activity)
        return@setOnMenuItemClickListener true
    }

    val userMenuBecomePr = userMenu?.subMenu?.findItem(R.id.userMenuBecomePr)
    val userMenuPractitioners = userMenu?.subMenu?.findItem(R.id.userMenuLogOut)
    val userMenuAccount = userMenu?.subMenu?.findItem(R.id.userMenuLogOut)
    val userMenuMyServices = userMenu?.subMenu?.findItem(R.id.userMenuLogOut)
    val userMenuNewArticle = userMenu?.subMenu?.findItem(R.id.userMenuLogOut)
    val userMenuAboutUs = userMenu?.subMenu?.findItem(R.id.userMenuAboutUs)
    val userMenuTerms = userMenu?.subMenu?.findItem(R.id.userMenuTerms)
    val userPrivacy = userMenu?.subMenu?.findItem(R.id.userMenuLogOut)

    when (role) {
        "user" -> {
            userMenuLogIn?.isVisible = false
            userMenuLogOut?.isVisible = true
            userMenuBecomePr?.isVisible = true
            userMenuPractitioners?.isVisible = true
            userMenuAccount?.isVisible = true
            userMenuMyServices?.isVisible = false
            userMenuNewArticle?.isVisible = false
        }
        "practitioner" -> {
            userMenuLogIn?.isVisible = false
            userMenuLogOut?.isVisible = true
            userMenuBecomePr?.isVisible = false
            userMenuPractitioners?.isVisible = true
            userMenuAccount?.isVisible = true
            userMenuMyServices?.isVisible = true
            userMenuNewArticle?.isVisible = true
        }
        else -> {
            userMenuLogIn?.isVisible = true
            userMenuBecomePr?.isVisible = true
            userMenuPractitioners?.isVisible = true
            userMenuAccount?.isVisible = false
            userMenuMyServices?.isVisible = false
            userMenuNewArticle?.isVisible = false
            userMenuLogOut?.isVisible = false
        }
    }
}