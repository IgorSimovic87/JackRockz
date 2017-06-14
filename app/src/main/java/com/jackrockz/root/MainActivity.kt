package com.jackrockz.root

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.jackrockz.R
import com.jackrockz.R.id.nav_view
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @JvmField var navItemIndex = 0
    @JvmField var CURRENT_TAG = "events"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    fun getHomeFragment() : Fragment {
        when (navItemIndex) {
            0 -> {

            }
        }
    }

    fun loadHomeFragment() {
        selectNavMenu()
        setToolbarTitle()

        if (supportFragmentManager.findFragmentByTag(CURRENT_TAG) != null) {
            drawer_layout.closeDrawers()
            return
        }

        val mPendingRunnable = Runnable {
            val fragment = getHomeFragment()
            val ft = supportFragmentManager.beginTransaction();
            ft.setCustomAnimations(
                    R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit);
            ft.replace(R.id.activity_base_content, fragment, CURRENT_TAG);
            ft.commitAllowingStateLoss();
        }
        mHandler.post(mPendingRunnable)
        drawer_layout.closeDrawers()
    }

    fun setUpNavigationView() {
        nav_view.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_events -> {
                    navItemIndex = 0
                    CURRENT_TAG = "events"
                }
            }

            menuItem.isChecked = !menuItem.isChecked

            true
        }
    }
}
