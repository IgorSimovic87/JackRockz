package com.jackrockz

import android.app.Application
import com.facebook.FacebookSdk

class MyApplication : Application() {
    companion object {
        lateinit var instance: MyApplication
            private set
    }

    var accessToken : String = ""

    override fun onCreate() {
        super.onCreate()

        instance = this
        FacebookSdk.sdkInitialize(applicationContext)
    }
}