package com.jackrockz.onboarding.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jackrockz.R
import com.jackrockz.commons.CityModel
import com.jackrockz.commons.ImageItem
import com.jackrockz.onboarding.WelcomeActivity
import com.jackrockz.onboarding.adapter.CityAdapter
import kotlinx.android.synthetic.main.fragment_select_city.*


class SelectCityFragment : Fragment(), View.OnClickListener {
    val listItems = ArrayList<CityModel>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_select_city, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val city = CityModel(0, "Barcelona", "4534545", "test@test.com", ImageItem("", "", "https://jack-rockz-prod.s3-eu-west-1.amazonaws.com/city/19/3a3f0b3eec53a8e34d0903e509b21bc486ae95bd.jpg", ""))
        listItems.add(city)
        listItems.add(city)
        listItems.add(city)
        listItems.add(city)

        recycler_view.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
        }
        recycler_view.adapter = CityAdapter(this, listItems)
    }

    override fun onClick(v: View?) {
        (activity as WelcomeActivity).changeFragment(SelectCityFragment())
    }
}

