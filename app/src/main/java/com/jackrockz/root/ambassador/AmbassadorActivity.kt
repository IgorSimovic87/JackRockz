package com.jackrockz.root.ambassador

import android.opengl.Visibility
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.jackrockz.R
import kotlinx.android.synthetic.main.toolbar.*

class AmbassadorActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ambassador)

        setSupportActionBar(toolbar)
        btnClose.visibility = View.VISIBLE
        btnClose.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v!!.id == R.id.btnClose) {
            onBackPressed()
        }
    }
}
