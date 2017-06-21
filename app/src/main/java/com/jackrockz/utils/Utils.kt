package com.jackrockz.utils

import android.app.Activity
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.jackrockz.R

class Utils {
    companion object {
        var progressDialog: Dialog? = null

        fun showLoading(activity: Activity) {
            progressDialog?.let { progressDialog!!.hide() }
            progressDialog = Dialog(activity)
            with(progressDialog!!) {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setContentView(R.layout.loading_dialog)
                setCancelable(false)
                setCanceledOnTouchOutside(false)
                window.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

                show()
            }
        }

        fun hideLoading() {
            if (progressDialog == null)
                return
            progressDialog!!.hide()
            progressDialog = null
        }
    }
}