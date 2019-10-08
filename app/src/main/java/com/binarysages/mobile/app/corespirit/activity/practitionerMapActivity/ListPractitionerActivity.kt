package com.binarysages.mobile.app.corespirit.activity.practitionerMapActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.activity.BaseActivity
import com.binarysages.mobile.app.corespirit.activity.categoryId
import com.binarysages.mobile.app.corespirit.activity.isMainScreen
import com.binarysages.mobile.app.corespirit.activity.practitionerProfileActivity.PractitionerInfoActivity
import com.binarysages.mobile.app.corespirit.models.PractitionerModel
import com.binarysages.mobile.app.corespirit.models.PractitionersModel
import com.binarysages.mobile.app.corespirit.network.NetworkService
import kotlinx.android.synthetic.main.load_activity_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListPractitionerActivity : BaseActivity() {
    init {
        isMainScreen = false
    }

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

        val practitionerAdapter = ListPractitionerAdapter(
            LOAD_LAYOUT,
            listener
        )

        NetworkService
            .getInstance()
            .getJsonApi()
            .getPractitioner(categoryId)
            .enqueue(object : Callback<PractitionersModel> {
                override fun onFailure(call: Call<PractitionersModel>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(
                    call: Call<PractitionersModel>,
                    response: Response<PractitionersModel>
                ) {
                    practitionerAdapter.notifyChange(response.body()?.data!!)
                }
            })

        practitionerListRecycleView.adapter = practitionerAdapter

        var count = 0
        practitionerListRecycleView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (manager.itemCount - 3 == manager.findLastVisibleItemPosition()) {
                        practitionerAdapter.addPractitioner(
                            categoryId,
                            count
                        )
                        count += 20
                    }
                }
            }
        )
    }
}