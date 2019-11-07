package com.binarysages.mobile.app.corespirit.ui.practitionerfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.recycleview.practitionersHomeList.PractitionersHomeAdapter
import kotlinx.android.synthetic.main.practitioner_fragment.*


class PractitionerFragment : Fragment() {
    private lateinit var viewModel: PractitionerViewModel
    private var practitionerAdapter = PractitionersHomeAdapter()
    private val mLayoutManager: LinearLayoutManager = LinearLayoutManager(context)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.practitioner_fragment, container, false)
    }

    private fun initView() {
        recycleViewPractitionerList.adapter = practitionerAdapter
        recycleViewPractitionerList.layoutManager = mLayoutManager

        recycleViewPractitionerList
            .addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    Log.d("># ", mLayoutManager.findFirstVisibleItemPosition().toString())
//                    Log.d("># ", mLayoutManager.findLastVisibleItemPosition().toString())
//                    Log.d("># ","###")
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PractitionerViewModel::class.java)

        initView()

        viewModel
            .getPractitioners()
            .observe(viewLifecycleOwner, Observer {
                practitionerAdapter.addPractitioners(it.data)
            })
    }
}
