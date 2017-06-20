package com.jackrockz.onboarding.fragments

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jackrockz.MyApplication
import com.jackrockz.R
import com.jackrockz.commons.RxBaseFragment
import com.jackrockz.onboarding.WelcomeActivity
import com.jackrockz.utils.GlobalConstants
import kotlinx.android.synthetic.main.fragment_select_country.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SelectCountryFragment : RxBaseFragment(), View.OnClickListener {
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
            MyApplication.instance.saveData(GlobalConstants.PREFS_COUNTRY, v.tag as String)

            val subscription = apiManager.putMe(v.tag as String)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe (
                            { _ ->
                                (activity as WelcomeActivity).changeFragment(SelectCityFragment())
                            },
                            { e ->
                                Snackbar.make(view!!, e.message ?: "", Snackbar.LENGTH_LONG).show()
                            }
                    )

            subscriptions.add(subscription)
        }
    }
}
