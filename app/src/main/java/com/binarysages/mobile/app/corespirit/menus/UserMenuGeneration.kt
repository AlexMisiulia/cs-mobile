package com.binarysages.mobile.app.corespirit.menus

import android.app.Activity
import android.content.Intent
import android.view.Menu
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.activity.loginActivity.LoginActivity
import com.binarysages.mobile.app.corespirit.activity.practitionerMapActivity.ListPractitionerActivity
import com.binarysages.mobile.app.corespirit.activity.staticPageActivity.StaticPageActivity

private var role: String? = null

fun generateUserMenu(menu: Menu?, newRole: String?, activity: Activity) {
    newRole?.let {
        role = newRole
    }

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
        generateUserMenu(menu, "guest", activity)
        return@setOnMenuItemClickListener true
    }

    val userMenuBecomePr = userMenu?.subMenu?.findItem(R.id.userMenuBecomePr)

    val userMenuPractitioners = userMenu?.subMenu?.findItem(R.id.userMenuPractitioners)
    userMenuPractitioners?.setOnMenuItemClickListener {
        activity.startActivity(Intent(activity, ListPractitionerActivity::class.java))
        return@setOnMenuItemClickListener true
    }

    val userMenuAccount = userMenu?.subMenu?.findItem(R.id.userMenuAccount)
    val userMenuMyServices = userMenu?.subMenu?.findItem(R.id.userMenuMyServices)
    val userMenuNewArticle = userMenu?.subMenu?.findItem(R.id.userMenuNewArticle)
    val userMenuAboutUs = userMenu?.subMenu?.findItem(R.id.userMenuAboutUs)

    userMenuAboutUs?.setOnMenuItemClickListener {
        val intent = Intent(activity, StaticPageActivity::class.java)
        intent.putExtra("page", "about")
        activity.startActivity(intent)
        return@setOnMenuItemClickListener true
    }

    val userMenuTerms = userMenu?.subMenu?.findItem(R.id.userMenuTerms)
    userMenuTerms?.setOnMenuItemClickListener {
        val intent = Intent(activity, StaticPageActivity::class.java)
        intent.putExtra("page", "terms")
        activity.startActivity(intent)
        return@setOnMenuItemClickListener true
    }

    val userPrivacy = userMenu?.subMenu?.findItem(R.id.userMenuPrivacy)
    userPrivacy?.setOnMenuItemClickListener {
        val intent = Intent(activity, StaticPageActivity::class.java)
        intent.putExtra("page", "privacy")
        activity.startActivity(intent)
        return@setOnMenuItemClickListener true
    }

    when (role) {
//        "user" -> {
//            userMenuLogIn?.isVisible = false
//            userMenuLogOut?.isVisible = true
//            userMenuBecomePr?.isVisible = true
//            userMenuPractitioners?.isVisible = true
//            userMenuAccount?.isVisible = true
//            userMenuMyServices?.isVisible = false
//            userMenuNewArticle?.isVisible = false
//        }
//        "practitioner" -> {
//            userMenuLogIn?.isVisible = false
//            userMenuLogOut?.isVisible = true
//            userMenuBecomePr?.isVisible = false
//            userMenuPractitioners?.isVisible = true
//            userMenuAccount?.isVisible = true
//            userMenuMyServices?.isVisible = true
//            userMenuNewArticle?.isVisible = true
//        }
        else -> {
            userMenuLogIn?.isVisible = false
            userMenuBecomePr?.isVisible = false
            userMenuPractitioners?.isVisible = true
            userMenuAccount?.isVisible = false
            userMenuMyServices?.isVisible = false
            userMenuNewArticle?.isVisible = false
            userMenuLogOut?.isVisible = false
        }
    }
}