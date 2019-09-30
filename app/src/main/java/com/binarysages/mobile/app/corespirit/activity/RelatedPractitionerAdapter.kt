package com.binarysages.mobile.app.corespirit.activity
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.binarysages.mobile.app.corespirit.R
//import kotlinx.android.synthetic.main.practitioner_carousel.view.*
//
//class ItemAdapter(val itemClick: (position: Int, item: Item) -> Unit) :
//
//    RecyclerView.Adapter<ItemViewHolder>() {
//
//    private var items: List<Item> = listOf()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
//        ItemViewHolder(
//            LayoutInflater.from(parent.context).inflate(
//                R.layout.practitioner_carousel,
//                parent,
//                false
//            )
//        )
//
//    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
//        holder.bind(items[position])
//        holder.itemView.setOnClickListener {
//            itemClick(position, items[position])
//        }
//    }
//
//
//    override fun getItemCount() = items.size
//
//    fun setItems(newItems: List<Item>) {
//        items = newItems
//        notifyDataSetChanged()
//    }
//}
//
//class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
//    fun bind(item: Item) {
//        view.list_item_text.text = "${item.title}"
//        view.list_item_icon.setImageResource(item.icon)
//    }
//}