package com.binarysages.mobile.app.corespirit.ui.practitioner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.binarysages.mobile.app.corespirit.R


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
        // TODO: Use the ViewModel
    }

}
