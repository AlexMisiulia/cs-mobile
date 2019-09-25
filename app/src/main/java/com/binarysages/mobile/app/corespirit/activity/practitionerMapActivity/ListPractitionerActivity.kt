package com.binarysages.mobile.app.corespirit.activity.practitionerMapActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.activity.BaseActivity
import com.binarysages.mobile.app.corespirit.activity.practitionerProfileActivity.PractitionerInfoActivity
import com.binarysages.mobile.app.corespirit.models.PractitionerModel

class ListPractitionerActivity : BaseActivity() {
    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState, R.layout.list_practitioner_map_layout)

        val practitionerListRecycleView: RecyclerView = findViewById(R.id.practitionerListMap)
        val manager = LinearLayoutManager(this)

        practitionerListRecycleView.layoutManager = manager

        val listener = object : ListPractitionerAdapter.onPractitionerClickListener {
            override fun practitionerCLick(practitioner: PractitionerModel) {
                val intent =
                    Intent(this@ListPractitionerActivity, PractitionerInfoActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable("image", practitioner.image)
                intent.putExtra("imgURL", bundle)
                intent.putExtra("userName", practitioner.title)
                intent.putExtra("bio", practitioner.bio)
                intent.putExtra("address", practitioner.address)
                intent.putExtra("webSite", practitioner.webSite)
                intent.putExtra("phone", practitioner.phone)
                startActivity(intent)
            }
        }

        val practitionerAdapter = ListPractitionerAdapter(findViewById(R.id.LOAD_LAYOUT), listener)
        practitionerListRecycleView.adapter = practitionerAdapter

        practitionerListRecycleView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    var count = 0
                    super.onScrolled(recyclerView, dx, dy)
                    if (manager.itemCount - 3 == manager.findLastVisibleItemPosition()) {
                        practitionerAdapter.addPractitioner(
                            count,
                            findViewById(R.id.LOAD_MORE_LAYOUT)
                        )
                    }
                    count += 20
                }
            })
    }
}