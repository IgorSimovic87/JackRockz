package com.jackrockz.commons

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.jackrockz.api.ApiManager
import rx.subscriptions.CompositeSubscription
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

open class RxBaseActivity : AppCompatActivity() {
    protected var subscriptions = CompositeSubscription()
    protected val apiManager by lazy { ApiManager() }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        subscriptions = CompositeSubscription()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (!subscriptions.isUnsubscribed) {
            subscriptions.unsubscribe()
        }
        subscriptions.clear()
    }

    override fun attachBaseContext(newBase: Context?) {
        // Custom Font
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

}