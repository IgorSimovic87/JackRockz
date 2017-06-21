package com.jackrockz

import android.app.Application
import android.content.SharedPreferences
import com.facebook.FacebookSdk
import com.google.gson.Gson
import com.jackrockz.api.EventModel
import com.jackrockz.utils.GlobalConstants

class MyApplication : Application() {
    companion object {
        lateinit var instance: MyApplication
            private set
    }

    var accessToken: String = ""
    val settings by lazy { getSharedPreferences(GlobalConstants.PREFS_NAME, 0) }
    val editor by lazy { settings.edit() }
    lateinit var currentEvent: EventModel

    override fun onCreate() {
        FacebookSdk.sdkInitialize(applicationContext)
        super.onCreate()

        instance = this
    }

    fun saveData(key: String, value: String) = editor.putString(key, value).commit()
    fun loadData(key: String) = settings.getString(key, "")

    fun saveObject(key: String, value: Any) = editor.putString(key, Gson().toJson(value)).commit()
    fun <T> loadObject(key: String, cls: Class<T>) = Gson().fromJson(loadData(key), cls)

}