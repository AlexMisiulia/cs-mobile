package com.binarysages.mobile.app.corespirit.ui.practitionerfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
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
//        add adapter
        recycleViewPractitionerList.adapter = practitionerAdapter
//        add layout manager
        recycleViewPractitionerList.layoutManager = mLayoutManager
//        add scroll listener
        recycleViewPractitionerList
            .addOnScrollListener(object : RecyclerView.OnScrollListener() {
                var pastVisiblesItems: Int = 0
                var visibleItemCount: Int = 0
                var totalItemCount: Int = 0

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        visibleItemCount = mLayoutManager.childCount
                        totalItemCount = mLayoutManager.itemCount
                        pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition()
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            viewModel
                                .loadPractitioner()
                            viewModel
                                .isLoadComplete.postValue(false)
                        }
                    }
                }
            })
        /*
        Observer isLoadComplete.
        After load complete, remove spinner
         */
        viewModel
            .isLoadComplete().observe(viewLifecycleOwner, Observer {
                if (it) spinKitPractitioner.visibility = ConstraintLayout.GONE
                else spinKitPractitioner.visibility = ConstraintLayout.VISIBLE
            })

        viewModel
            .getPractitioners()
            .observe(viewLifecycleOwner, Observer {
                practitionerAdapter.addPractitioners(it.data)
            })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PractitionerViewModel::class.java)

        initView()
    }
}
