package com.jackrockz

import android.app.Application
import com.facebook.FacebookSdk

/**
 * Created by minnie on 6/17/17.
 */
class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        FacebookSdk.sdkInitialize(applicationContext)
    }
}