package com.jackrockz.root.tickets

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jackrockz.R
import kotlinx.android.synthetic.main.fragment_my_tickets.*

class MyTicketsFragment : Fragment(), View.OnClickListener {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_my_tickets, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layout_item1.setOnClickListener(this)
        layout_item2.setOnClickListener(this)
        layout_item3.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        startActivity(Intent(activity, TicketDetailActivity::class.java))
    }

}
