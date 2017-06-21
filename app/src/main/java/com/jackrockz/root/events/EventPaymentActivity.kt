package com.jackrockz.root.events

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.NumberPicker
import com.jackrockz.MyApplication
import com.jackrockz.R
import com.jackrockz.api.EventModel
import com.jackrockz.commons.RxBaseActivity
import kotlinx.android.synthetic.main.activity_event_payment.*
import kotlinx.android.synthetic.main.dialog_number_picker.*
import java.text.SimpleDateFormat
import java.util.*


class EventPaymentActivity : RxBaseActivity(), View.OnClickListener {
    lateinit var event: EventModel

    val myFormat = "MMM dd, yyyy"
    val sdf = SimpleDateFormat(myFormat, Locale.US)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_payment)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        event = MyApplication.instance.currentEvent

        txtTitle.text = event.title + " - " + event.subtitle
//        txtDate.text = sdf.format(event.start_date)
        txtVenue.text = event.venue.name + ", " + event.venue.city

        txtQuantity.setOnClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.txtQuantity -> OnQuantity()
        }
    }

    fun OnQuantity() {
        val d = Dialog(this)
        d.setTitle("Select Quantity")
        d.setContentView(R.layout.dialog_number_picker)

        val np = d.findViewById(R.id.numberPicker1) as NumberPicker
        val button1 = d.findViewById(R.id.button1)
        val button2 = d.findViewById(R.id.button2)

        np.maxValue = 100
        np.minValue = 1
        np.wrapSelectorWheel = false
        button1.setOnClickListener {
            txtQuantity.setText(np.value.toString())
            d.dismiss()
        }
        button2.setOnClickListener{
            d.dismiss()
        }
        d.show()
    }
}
