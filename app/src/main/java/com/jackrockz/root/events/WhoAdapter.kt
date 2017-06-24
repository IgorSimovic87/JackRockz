package com.jackrockz.root.events

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.jackrockz.R
import com.jackrockz.api.VisitorModel
import com.jackrockz.commons.extensions.inflate
import com.jackrockz.commons.extensions.loadImg
import kotlinx.android.synthetic.main.who_image_item.view.*

/**
 * Created by minnie on 6/24/17.
 */
class WhoAdapter(val activity: EventDetailActivity, val items: ArrayList<VisitorModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = VisitorHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as VisitorHolder
        holder.bind(items[position])
    }

    inner class VisitorHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.who_image_item)) {
        fun bind(item: VisitorModel) = with(itemView) {
            imgView.loadImg(item.image)

            tag = item
            setOnClickListener (activity)
        }
    }
}