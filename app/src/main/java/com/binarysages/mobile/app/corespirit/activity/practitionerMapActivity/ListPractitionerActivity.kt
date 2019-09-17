package com.binarysages.mobile.app.corespirit.activity.practitionerMapActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Layout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.activity.BaseActivity

class ListPractitionerActivity : BaseActivity(){
    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState, R.layout.list_practitioner_map_layout)

        val practitionerListRecycleView: RecyclerView =  findViewById(R.id.practitionerListMap)
        practitionerListRecycleView.layoutManager = LinearLayoutManager(this)
        practitionerListRecycleView.adapter = ListPractitionerAdapter()
    }
}