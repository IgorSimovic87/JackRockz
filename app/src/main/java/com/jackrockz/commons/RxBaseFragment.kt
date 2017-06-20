package com.jackrockz.commons

import android.support.v4.app.Fragment
import com.jackrockz.api.ApiManager
import rx.subscriptions.CompositeSubscription

open class RxBaseFragment() : Fragment() {
    protected var subscriptions = CompositeSubscription()
    protected val apiManager by lazy { ApiManager() }

    override fun onResume() {
        super.onResume()
        if (!subscriptions.hasSubscriptions()) {
            subscriptions = CompositeSubscription()
        }
    }

    override fun onPause() {
        super.onPause()
        if (!subscriptions.isUnsubscribed) {
            subscriptions.unsubscribe()
        }
        subscriptions.clear()
    }
}