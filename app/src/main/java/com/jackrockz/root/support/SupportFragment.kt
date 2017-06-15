package com.jackrockz.root.support

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jackrockz.R

class SupportFragment : Fragment(), View.OnClickListener {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_support, container, false)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btnContact) {

        }
    }

}
