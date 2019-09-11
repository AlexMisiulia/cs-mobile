package com.binarysages.mobile.app.corespirit.menus

import android.app.Activity
import android.content.Intent
import android.view.Menu
import com.binarysages.mobile.app.corespirit.LoginActivity
import com.binarysages.mobile.app.corespirit.R

fun generateUserMenu(menu: Menu?, role: String, activity: Activity) {
    val userMenu = menu?.findItem(R.id.userMenu)
    when (role) {
        "user" -> {
            userMenu?.subMenu?.findItem(R.id.userMenuLogIn)?.isVisible = false
            userMenu?.subMenu?.findItem(R.id.userMenuLogOut)?.isVisible = true
            userMenu?.subMenu?.findItem(R.id.userMenuBecomePr)?.isVisible = true
            userMenu?.subMenu?.findItem(R.id.userMenuPractitioners)?.isVisible = true
            userMenu?.subMenu?.findItem(R.id.userMenuAccount)?.isVisible = true
            userMenu?.subMenu?.findItem(R.id.userMenuMyServices)?.isVisible = false
            userMenu?.subMenu?.findItem(R.id.userMenuNewArticle)?.isVisible = false
        }
        "practitioner" -> {
            userMenu?.subMenu?.findItem(R.id.userMenuLogIn)?.isVisible = false
            userMenu?.subMenu?.findItem(R.id.userMenuLogOut)?.isVisible = true
            userMenu?.subMenu?.findItem(R.id.userMenuBecomePr)?.isVisible = false
            userMenu?.subMenu?.findItem(R.id.userMenuPractitioners)?.isVisible = true
            userMenu?.subMenu?.findItem(R.id.userMenuAccount)?.isVisible = true
            userMenu?.subMenu?.findItem(R.id.userMenuMyServices)?.isVisible = true
            userMenu?.subMenu?.findItem(R.id.userMenuNewArticle)?.isVisible = true
        }
        else -> {
            userMenu?.subMenu?.findItem(R.id.userMenuLogIn)?.isVisible = true
            userMenu?.subMenu?.findItem(R.id.userMenuLogIn)?.setOnMenuItemClickListener {
                val intent = Intent(activity,LoginActivity::class.java)
                activity.startActivity(intent)
                return@setOnMenuItemClickListener true
            }

            userMenu?.subMenu?.findItem(R.id.userMenuBecomePr)?.isVisible = true
            userMenu?.subMenu?.findItem(R.id.userMenuPractitioners)?.isVisible = true
            userMenu?.subMenu?.findItem(R.id.userMenuAccount)?.isVisible = false
            userMenu?.subMenu?.findItem(R.id.userMenuMyServices)?.isVisible = false
            userMenu?.subMenu?.findItem(R.id.userMenuNewArticle)?.isVisible = false
            userMenu?.subMenu?.findItem(R.id.userMenuLogOut)?.isVisible = false
        }
    }
}