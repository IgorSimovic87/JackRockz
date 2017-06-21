package com.jackrockz.onboarding.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.jackrockz.R
import com.jackrockz.commons.RxBaseFragment
import com.jackrockz.onboarding.WelcomeActivity
import com.jackrockz.utils.Utils
import kotlinx.android.synthetic.main.fragment_arrival_date.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class ArrivalDateFragment : RxBaseFragment(), View.OnClickListener {
    val myCalendar = Calendar.getInstance()!!
    val date = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        myCalendar.set(year, month, dayOfMonth, 0, 0)
        if ((view.tag as Int) == R.id.txtArrivalDate) {
            arrivalDate = myCalendar.time
            updateLabel(txtArrivalDate, arrivalDate!!)
        } else {
            departureDate = myCalendar.time
            updateLabel(txtDepartureDate, departureDate!!)
        }
    }
    val myFormat = "dd-MM-yyyy"
    val sdf = SimpleDateFormat(myFormat, Locale.US)
    var arrivalDate: Date? = null
    var departureDate: Date? = null

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
            R.id.txtArrivalDate -> {
                myCalendar.time = arrivalDate ?: Date()
                val picker = DatePickerDialog(activity, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH))
                picker.datePicker.tag = v.id
                picker.show()
            }
            R.id.txtDepartureDate -> {
                myCalendar.time = departureDate ?: Date()
                val picker = DatePickerDialog(activity, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH))
                picker.datePicker.tag = v.id
                picker.show()
            }
            R.id.btnNext -> {
                Utils.showLoading(activity)
                val subscription = apiManager.putMe(arrivalDate = arrivalDate, departureDate = departureDate).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe (
                                { _ ->
                                    Utils.hideLoading()
                                    (activity as WelcomeActivity).gotoNextActivity()
                                },
                                { e ->
                                    Utils.hideLoading()
                                    Snackbar.make(view!!, e.message ?: "", android.support.design.widget.Snackbar.LENGTH_LONG).show()
                                }
                        )
                subscriptions.add(subscription)
            }
        }
    }

    fun updateLabel(v: EditText, date: Date) {
        v.setText(sdf.format(date.time))
    }
}