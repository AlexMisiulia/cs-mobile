package com.binarysages.mobile.app.corespirit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.binarysages.mobile.app.corespirit.ui.catalog.CatalogFragment
import com.binarysages.mobile.app.corespirit.ui.events.EventsFragment
import com.binarysages.mobile.app.corespirit.ui.home.HomeFragment
import com.binarysages.mobile.app.corespirit.ui.practitioner.PractitionerFragment
import com.binarysages.mobile.app.corespirit.ui.profile.ProfileFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val manager: FragmentTransaction = supportFragmentManager.beginTransaction()

        val navView: ChipNavigationBar = findViewById(R.id.bottom_navigation)
        navView.setItemSelected(R.id.bottom_menu_home)
        /**
         * TODO: Replace duplicate code
         */
        manager.replace(R.id.main_fragment, HomeFragment())
        manager.commit()

        navView.setOnItemSelectedListener {
            when (it) {
                R.id.bottom_menu_home -> {
                    manager.replace(R.id.main_fragment, HomeFragment())
                    manager.commit()
                }
                R.id.bottom_menu_practitioners -> {
                    manager.replace(R.id.main_fragment, PractitionerFragment())
                    manager.commit()
                }
                R.id.bottom_menu_catalog -> {
                    manager.replace(R.id.main_fragment, CatalogFragment())
                    manager.commit()
                }
                R.id.bottom_menu_events -> {
                    manager.replace(R.id.main_fragment, EventsFragment())
                    manager.commit()
                }
                R.id.bottom_menu_profile -> {
                    manager.replace(R.id.main_fragment, ProfileFragment())
                    manager.commit()
                }
            }
        }
    }
}