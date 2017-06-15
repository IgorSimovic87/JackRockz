package com.jackrockz.root.events

import android.graphics.Paint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jackrockz.R
import kotlinx.android.synthetic.main.activity_event_detail.*


class EventDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        txtStroke.paintFlags = txtStroke.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }
}
