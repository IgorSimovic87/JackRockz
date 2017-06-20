package com.jackrockz.onboarding.fragments

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jackrockz.R
import com.jackrockz.api.CityModel
import com.jackrockz.commons.RxBaseFragment
import com.jackrockz.onboarding.WelcomeActivity
import com.jackrockz.onboarding.adapter.CityAdapter
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.android.synthetic.main.fragment_select_city.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SelectCityFragment : RxBaseFragment(), View.OnClickListener {
    var listItems = ArrayList<CityModel>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_select_city, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subscription = apiManager.getCities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        { aryCities ->
                            listItems = aryCities as ArrayList<CityModel>
                            recycler_view.adapter = CityAdapter(this, listItems)
                        },
                        { e ->
                            Snackbar.make(contentView, e.message ?: "", Snackbar.LENGTH_LONG).show()
                        }
                )
        subscriptions.add(subscription)

        recycler_view.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            linearLayout.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayout
            adapter = CityAdapter(this@SelectCityFragment, listItems)
        }
    }

    override fun onClick(v: View) {
        val subscription = apiManager.putMe(city = v.tag as String)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        { _ ->

                            (activity as WelcomeActivity).changeFragment(ArrivalDateFragment())

                        },
                        { e ->
                            Snackbar.make(contentView, e.message ?: "", android.support.design.widget.Snackbar.LENGTH_LONG).show()
                        }
                )
        subscriptions.add(subscription)
    }
}

