package com.binarysages.mobile.app.corespirit.recycleview.practitionercarousel

import android.view.View
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.practitioners.Practitioner
import com.github.islamkhsh.CardSliderAdapter

class PractitionerCaouselAdapter(practitioners: ArrayList<Practitioner>) :
    CardSliderAdapter<Practitioner>(practitioners) {
    override fun bindView(position: Int, itemContentView: View, item: Practitioner?) {

    }
    override fun getItemContentLayout(position: Int) = R.layout.practitioner_carousel_item
}