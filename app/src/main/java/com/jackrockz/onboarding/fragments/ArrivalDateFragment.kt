package com.jackrockz.onboarding.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.jackrockz.R
import com.jackrockz.onboarding.WelcomeActivity
import kotlinx.android.synthetic.main.fragment_arrival_date.*
import java.text.SimpleDateFormat
import java.util.*

class ArrivalDateFragment : Fragment(), View.OnClickListener {
    val myCalendar = Calendar.getInstance()
    val date = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        myCalendar.set(Calendar.YEAR, year)
        myCalendar.set(Calendar.MONTH, month)
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        updateLabel(if ((view.tag as Int) == R.id.txtArrivalDate) txtArrivalDate else  txtDepartureDate)
    }
    val myFormat = "dd-MM-yyyy"
    val sdf = SimpleDateFormat(myFormat, Locale.US)


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_arrival_date, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtArrivalDate.setOnClickListener(this)
        txtDepartureDate.setOnClickListener(this)
        btnNext.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.txtArrivalDate, R.id.txtDepartureDate -> {
                val picker = DatePickerDialog(activity, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH))
                picker.datePicker.tag = v.id
                picker.show()
            }
            R.id.btnNext -> {
                (activity as WelcomeActivity).gotoNextActivity()
            }
        }
    }

    fun updateLabel(v: EditText) {
        v.setText(sdf.format(myCalendar.time))
    }
}