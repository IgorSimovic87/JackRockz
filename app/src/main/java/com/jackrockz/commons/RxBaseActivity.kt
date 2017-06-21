package com.jackrockz.commons

import android.support.v7.app.AppCompatActivity
import com.jackrockz.api.ApiManager
import rx.subscriptions.CompositeSubscription

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

}