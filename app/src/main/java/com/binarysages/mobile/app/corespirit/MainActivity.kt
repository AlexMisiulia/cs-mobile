package com.binarysages.mobile.app.corespirit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.binarysages.mobile.app.corespirit.ui.articlesfragment.ArticlesFragment
import com.binarysages.mobile.app.corespirit.ui.eventsfragment.EventsFragment
import com.binarysages.mobile.app.corespirit.ui.homefragment.HomeFragment
import com.binarysages.mobile.app.corespirit.ui.practitionerfragment.PractitionerFragment
import com.binarysages.mobile.app.corespirit.ui.profilefragment.ProfileFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val navView: ChipNavigationBar = findViewById(R.id.bottom_navigation)
        navView.setItemSelected(R.id.bottom_menu_home)

        navigateToFragment(HomeFragment())

        navView.setOnItemSelectedListener {
            when (it) {
                R.id.bottom_menu_home -> {
                    navigateToFragment(HomeFragment())
                }
                R.id.bottom_menu_practitioners -> {
                    navigateToFragment(PractitionerFragment())
                }
                R.id.bottom_menu_articles -> {
                    navigateToFragment(ArticlesFragment())
                }
                R.id.bottom_menu_events -> {
                    navigateToFragment(EventsFragment())
                }
                R.id.bottom_menu_profile -> {
                    navigateToFragment(ProfileFragment())
                }
            }
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        val manager: FragmentTransaction = supportFragmentManager.beginTransaction()
        manager.add(R.id.main_fragment, fragment)
        manager.commit()
    }
}