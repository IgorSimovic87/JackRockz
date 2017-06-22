package com.jackrockz

import android.app.Application
import com.facebook.FacebookSdk
import com.jackrockz.api.EventModel
import com.jackrockz.api.TicketModel
import com.jackrockz.api.UserModel

class MyApplication : Application() {
    companion object {
        lateinit var instance: MyApplication
            private set
    }

    var accessToken: String = ""
    lateinit var currentEvent: EventModel
    lateinit var currentUser: UserModel
    lateinit var currentTicket: TicketModel

    override fun onCreate() {
        FacebookSdk.sdkInitialize(applicationContext)
        super.onCreate()

        instance = this
    }

}