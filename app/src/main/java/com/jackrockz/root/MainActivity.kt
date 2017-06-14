package com.jackrockz.root

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jackrockz.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @JvmField var navItemIndex = 0
    @JvmField var CURRENT_TAG = "events"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportActionBar!!.show()
    }

    fun setUpNavigationView() {
        nav_view.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_events -> {
                    navItemIndex = 0
                    CURRENT_TAG = "events"
                }
            }
            true
        }
    }
}
