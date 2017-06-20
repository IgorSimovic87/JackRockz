package com.jackrockz.root.events

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jackrockz.MyApplication
import com.jackrockz.R
import com.jackrockz.api.CityModel
import com.jackrockz.api.EventModel
import com.jackrockz.commons.RxBaseFragment
import com.jackrockz.utils.GlobalConstants
import kotlinx.android.synthetic.main.fragment_events.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class EventsFragment : RxBaseFragment(), View.OnClickListener {
    val myCalendar = Calendar.getInstance()
    val date = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        myCalendar.set(year, month, dayOfMonth, 0, 0)
        updateLabel()
    }
    val myFormat = "MMM dd, yyyy"
    val sdf = SimpleDateFormat(myFormat, Locale.US)
    var listItems = ArrayList<EventModel>()
    val cityID = MyApplication.instance.loadObject(GlobalConstants.PREFS_CITY, CityModel::class.java).id

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GetEvents()
        updateLabel()

        btnDate.setOnClickListener(this)

        recycler_view.apply {
            setHasFixedSize(true)
            val layout = GridLayoutManager(activity, 2)
            layout.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(p0: Int): Int = when(p0){
                    0 -> 2
                    else -> 1
                }
            }
            layoutManager = layout
            adapter = EventsAdapter(this@EventsFragment, listItems)
        }
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
        GetEvents()
        txtDate.setText(sdf.format(myCalendar.time))
    }

    fun GetEvents() {
        val subscription = apiManager.getEvents(cityID, myCalendar.time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        { aryEvents ->
                            listItems = aryEvents as ArrayList<EventModel>
                            recycler_view.adapter = EventsAdapter(this, listItems)
                        },
                        { e ->
                            Snackbar.make(view!!, e.message ?: "", Snackbar.LENGTH_LONG).show()
                        }
                )
        subscriptions.add(subscription)
    }
}
