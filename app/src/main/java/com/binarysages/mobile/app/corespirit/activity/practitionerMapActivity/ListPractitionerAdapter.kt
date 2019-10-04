package com.binarysages.mobile.app.corespirit.activity.practitionerMapActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.binarysages.mobile.app.corespirit.R
import com.binarysages.mobile.app.corespirit.models.PractitionerModel
import com.binarysages.mobile.app.corespirit.models.PractitionersModel
import com.binarysages.mobile.app.corespirit.network.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListPractitionerAdapter(
    var itemVIew: ConstraintLayout,
    private val practitionerClickListener: onPractitionerClickListener
) :
    RecyclerView.Adapter<ListPractitionerMapHolder>() {

    interface onPractitionerClickListener {
        fun practitionerCLick(practitioner: PractitionerModel): Unit
    }

    private var practitionerList: Array<PractitionerModel>? = Array(0, PractitionersModel)


    fun addPractitioner(count: Int = 0, itemVIew: ConstraintLayout? = null) {
        itemVIew?.let {
            this.itemVIew = it
            this.notifyDataSetChanged()
        }

        NetworkService.getInstance().getJsonApi().getPractitioner(itemCount).enqueue(
            object : Callback<PractitionersModel> {
                override fun onFailure(call: Call<PractitionersModel>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(
                    call: Call<PractitionersModel>,
                    response: Response<PractitionersModel>
                ) {
                    practitionerList = practitionerList?.plus(response.body()?.data!!)
                    notifyDataSetChanged()
                }
            }
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPractitionerMapHolder {
        val articleItemVIew: View =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.practitioner_map_item, parent, false)
        return ListPractitionerMapHolder(articleItemVIew, practitionerClickListener)
    }

    override fun getItemCount(): Int {
        return practitionerList?.size!!
    }

    override fun onBindViewHolder(holder: ListPractitionerMapHolder, position: Int) {
        practitionerList?.get(position)?.let { holder.bind(it) }
    }
}