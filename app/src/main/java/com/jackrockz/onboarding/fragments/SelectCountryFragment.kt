package com.jackrockz.onboarding.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jackrockz.R
import com.jackrockz.onboarding.WelcomeActivity
import kotlinx.android.synthetic.main.fragment_select_country.*

class SelectCountryFragment : Fragment(), View.OnClickListener {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_select_country, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listOf(btnGermany, btnFrance, btnItaly, btnNetherlands, btnOther).forEach { button -> button.setOnClickListener(this) }
    }

    override fun onClick(v: View) {
        if (v.id in listOf(R.id.btnFrance, R.id.btnGermany, R.id.btnItaly, R.id.btnNetherlands, R.id.btnOther)) {
            (activity as WelcomeActivity).changeFragment(ArrivalDateFragment())
        }
    }
}
