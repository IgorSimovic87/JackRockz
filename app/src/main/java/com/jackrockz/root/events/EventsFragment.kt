package com.jackrockz.root.events

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jackrockz.R
import com.jackrockz.api.EventModel
import kotlinx.android.synthetic.main.fragment_events.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class EventsFragment : Fragment(), View.OnClickListener {
    val myCalendar = Calendar.getInstance()
    val date = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        myCalendar.set(Calendar.YEAR, year)
        myCalendar.set(Calendar.MONTH, month)
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        updateLabel()
    }
    val myFormat = "MMM dd, yyyy"
    val sdf = SimpleDateFormat(myFormat, Locale.US)
    var listItems = ArrayList<EventModel>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnDate.setOnClickListener(this)

        listItems = ArrayList<EventModel>()

        val event = EventModel(0, Date(), "country")
        listItems.add(event)
        listItems.add(event)
        listItems.add(event)
        listItems.add(event)
        listItems.add(event)
        listItems.add(event)
        listItems.add(event)
        listItems.add(event)

        val arySpan = ArrayList<Int>()

        recycler_view.apply {
            setHasFixedSize(true)
            val layout = GridLayoutManager(activity, 2)
            var isFullEnabled = true
            layout.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(p0: Int): Int {
                    if (arySpan.size > p0) {
                        return arySpan[p0]
                    }
                    if (isFullEnabled && Random().nextBoolean()) {
                        arySpan.add(2)
                        return arySpan[p0]
                    }
                    isFullEnabled = !isFullEnabled
                    arySpan.add(1)
                    return arySpan[p0]
                }
            }
            layoutManager = layout
        }
        recycler_view.adapter = EventsAdapter(this, listItems)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnDate -> {
                val picker = DatePickerDialog(activity, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH))
                picker.show()
            }
            else -> {
                startActivity(Intent(activity, EventDetailActivity::class.java))
            }
        }
    }

    fun updateLabel() {
        txtDate.setText(sdf.format(myCalendar.time))
    }
}
