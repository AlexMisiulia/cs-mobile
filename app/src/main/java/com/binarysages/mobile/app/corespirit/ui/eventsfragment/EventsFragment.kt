package com.binarysages.mobile.app.corespirit.ui.eventsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.recycleview.eventslist.EventsAdapter
import com.github.ybq.android.spinkit.SpinKitView
import kotlinx.android.synthetic.main.events_fragment.*

class EventsFragment : Fragment() {
    private lateinit var viewModel: EventsViewModel
    private val eventsAdapter: EventsAdapter = EventsAdapter()

    private fun initView() {
        recycleViewEventsList.layoutManager = LinearLayoutManager(context)
        recycleViewEventsList.adapter = eventsAdapter

        viewModel
            .getEventsList().observe(viewLifecycleOwner, Observer {
                eventsAdapter.addEvents(it.data.events)
            })

        viewModel
            .isLoadCompete()
            .observe(viewLifecycleOwner, Observer {
                spinKitLayout.visibility =
                    if (it) ConstraintLayout.GONE else ConstraintLayout.VISIBLE
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.events_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EventsViewModel::class.java)
        initView()
    }
}
