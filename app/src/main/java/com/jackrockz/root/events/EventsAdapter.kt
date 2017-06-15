package com.jackrockz.root.events

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.jackrockz.R
import com.jackrockz.commons.EventModel
import com.jackrockz.commons.extensions.inflate
import java.util.*

class EventsAdapter(val fragment: EventsFragment, val items: ArrayList<EventModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = EventHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as EventHolder
        holder.bind(items[position])
    }

    inner class EventHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.event_item)) {
        fun bind(item: EventModel) = with(itemView) {
//            txtTitle.text = item.target_country

            super.itemView.setOnClickListener (fragment)
        }
    }
}