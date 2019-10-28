package com.binarysages.mobile.app.corespirit.ui.practitionerfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.ui.practitionerlist.PractitionersAdapter
import com.github.ybq.android.spinkit.SpinKitView
import kotlinx.android.synthetic.main.practitioner_fragment.*


class PractitionerFragment : Fragment() {

    companion object {
        fun newInstance() = PractitionerFragment()
    }

    private lateinit var viewModel: PractitionerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.practitioner_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PractitionerViewModel::class.java)
        viewModel.getPractitioners().observe(viewLifecycleOwner, Observer {
            recycleViewPractitionerList.layoutManager = LinearLayoutManager(context)
            recycleViewPractitionerList.adapter = PractitionersAdapter(it)
            spinKitPractitioner.visibility = SpinKitView.GONE
        })
    }

}
