package com.jackrockz.root

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.facebook.login.LoginManager
import com.jackrockz.MyApplication
import com.jackrockz.R
import com.jackrockz.onboarding.WelcomeActivity
import com.jackrockz.root.ambassador.AmbassadorActivity
import com.jackrockz.root.events.EventsFragment
import com.jackrockz.root.support.SupportFragment
import com.jackrockz.root.tickets.MyTicketsFragment
import com.jackrockz.utils.GlobalConstants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {
    @JvmField var navItemIndex = 0
    @JvmField var CURRENT_TAG = "events"
    var mHandler: Handler = Handler()

    var activityTitles = arrayOf<String>()

    var isFromPayment = false

    var onBackPressedListener: OnBackPressedListener? = null
    interface OnBackPressedListener {
        fun doBack()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        isFromPayment = intent.getBooleanExtra(GlobalConstants.PREFS_ISFROMPAYMENT, false)

        mHandler = Handler()
        activityTitles = resources.getStringArray(R.array.nav_item_activity_titles)

        setUpNavigationView()

        if (isFromPayment) {
            navItemIndex = 1
            CURRENT_TAG = "my tickets"
            loadHomeFragment()
        } else if (savedInstanceState == null) {
            navItemIndex = 0
            CURRENT_TAG = "events"
            loadHomeFragment()
        }
    }

    override fun onResume() {
        super.onResume()

        nav_view.menu.findItem(R.id.nav_code).isVisible = MyApplication.instance.currentUser.ambassador == null
    }

    fun getHomeFragment(): Fragment = when (navItemIndex) {
        0 -> EventsFragment()
        1 -> MyTicketsFragment()
        2 -> SupportFragment()
        else -> EventsFragment()
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
        (nav_view.getHeaderView(0).findViewById(R.id.txtName) as TextView).text = MyApplication.instance.currentUser.first_name + " " + MyApplication.instance.currentUser.last_name
        nav_view.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_events -> {
                    navItemIndex = 0
                    CURRENT_TAG = "events"
                }
                R.id.nav_mytickets -> {
                    navItemIndex = 1
                    CURRENT_TAG = "my tickets"
                }
                R.id.nav_support -> {
                    navItemIndex = 2
                    CURRENT_TAG = "support"
                }
                R.id.nav_code -> {
                    drawer_layout.closeDrawer(Gravity.LEFT)
                    startActivity(Intent(this, AmbassadorActivity::class.java))
                    return@setNavigationItemSelectedListener true
                }
                R.id.nav_logout -> {
                    LoginManager.getInstance().logOut()
                    startActivity(Intent(this, WelcomeActivity::class.java))
                    finish()
                    return@setNavigationItemSelectedListener true
                }
            }

            menuItem.isChecked = !menuItem.isChecked

            loadHomeFragment()
            true
        }

        val actionBarDrawerToggle = object : ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            override fun onDrawerClosed(drawerView: View?) {
                super.onDrawerClosed(drawerView)
            }

            override fun onDrawerOpened(drawerView: View?) {
                super.onDrawerOpened(drawerView)
            }
        }

        drawer_layout.setDrawerListener(actionBarDrawerToggle)

        actionBarDrawerToggle.syncState()
    }

    fun setToolbarTitle() {
        supportActionBar!!.title = activityTitles[navItemIndex]
    }

    fun selectNavMenu() {
        nav_view.menu.getItem(navItemIndex).isChecked = true
    }

    override fun onBackPressed() {
        if (onBackPressedListener != null) {
            onBackPressedListener!!.doBack()
            return
        }

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawers()
            return
        }

        if (navItemIndex != 0) {
            navItemIndex = 0
            CURRENT_TAG = "events"
            loadHomeFragment()
            return
        }
        super.onBackPressed()
    }

}
