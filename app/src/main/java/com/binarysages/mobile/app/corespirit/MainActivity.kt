package com.binarysages.mobile.app.corespirit

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.binarysages.mobile.app.corespirit.ui.catalog.CatalogFragment
import com.binarysages.mobile.app.corespirit.ui.events.EventsFragment
import com.binarysages.mobile.app.corespirit.ui.home.HomeFragment
import com.binarysages.mobile.app.corespirit.ui.practitioner.PractitionerFragment
import com.binarysages.mobile.app.corespirit.ui.profile.ProfileFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val navView: ChipNavigationBar = findViewById(R.id.bottom_navigation)

        main_fragment_progress_bar.visibility = ProgressBar.GONE

        navView.setOnItemSelectedListener {
            main_fragment_progress_bar.visibility = ProgressBar.VISIBLE
            val manager: FragmentTransaction = supportFragmentManager.beginTransaction()
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