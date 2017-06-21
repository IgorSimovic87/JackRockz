package com.jackrockz.utils

import android.app.Activity
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AlertDialog
import android.view.Window
import com.google.gson.Gson
import com.jackrockz.MyApplication
import com.jackrockz.R


class Utils {
    companion object {
        var progressDialog: Dialog? = null
        val settings by lazy { MyApplication.instance.getSharedPreferences(GlobalConstants.PREFS_NAME, 0) }
        val editor by lazy { settings.edit() }

        fun showLoading(activity: Activity) {
            progressDialog?.hide()
            progressDialog = Dialog(activity)
            progressDialog?.apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setContentView(R.layout.loading_dialog)
                setCancelable(false)
                setCanceledOnTouchOutside(false)
                window.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

                show()
            }
        }

        fun hideLoading() {
            progressDialog?.hide()
            progressDialog = null
        }

        fun showAlertDialog(activity: Activity, title: String? = null, message: String) {
            AlertDialog.Builder(activity)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("OK", { dialog, _ -> dialog.dismiss() })
                    .show()
        }

        fun saveData(key: String, value: String) = editor.putString(key, value).commit()
        fun loadData(key: String) = settings.getString(key, "")

        fun saveObject(key: String, value: Any) = editor.putString(key, Gson().toJson(value)).commit()
        fun <T> loadObject(key: String, cls: Class<T>) = Gson().fromJson(loadData(key), cls)
    }
}