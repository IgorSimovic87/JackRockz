package com.jackrockz.root.events

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.jackrockz.R
import kotlinx.android.synthetic.main.activity_event_detail.*


class EventDetailActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        txtStroke.paintFlags = txtStroke.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        btnGet.setOnClickListener(this)
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
