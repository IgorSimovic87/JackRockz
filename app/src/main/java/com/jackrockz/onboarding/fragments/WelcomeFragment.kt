package com.jackrockz.onboarding.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.AccessToken
import com.facebook.login.LoginManager
import com.jackrockz.R
import com.jackrockz.onboarding.WelcomeActivity
import com.jackrockz.utils.Utils
import kotlinx.android.synthetic.main.fragment_welcome.*
import java.util.*

class WelcomeFragment : Fragment(), View.OnClickListener {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_welcome, container, false);
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnFacebook.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btnFacebook) {
            LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "email"));
        }
    }

}
