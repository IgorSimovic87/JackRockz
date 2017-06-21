package com.jackrockz.root.events

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.jackrockz.MyApplication
import com.jackrockz.R
import com.jackrockz.api.EventModel
import com.jackrockz.commons.extensions.loadImg
import kotlinx.android.synthetic.main.activity_event_detail.*

class EventDetailActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var event: EventModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        event = MyApplication.instance.currentEvent
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        txtStroke.paintFlags = txtStroke.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        btnGet.setOnClickListener(this)

        txtTitle.text = event.title
        txtSubtitle.text = event.subtitle
        txtViewsCount.text = event.views_count.toString() + "x"
        txtExpCount.text = event.experience_count.toString() + "x"
        txtGuestCount.text = event.guestlist_count.toString() + "x"
        txtPrice.text = event.price.toString()
        txtStroke.text = event.regular_price.toString()

        event.image?.let { imgView.loadImg(event.image!!.medium) }

        supportActionBar!!.title = event.venue.name
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        startActivity(Intent(this, EventPaymentActivity::class.java))
    }
}
