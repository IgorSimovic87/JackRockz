package com.jackrockz.onboarding.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.jackrockz.R
import com.jackrockz.onboarding.WelcomeActivity
import kotlinx.android.synthetic.main.fragment_welcome.*
import java.util.*

class WelcomeFragment : Fragment(), View.OnClickListener {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_welcome, container, false);
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnFacebook.setOnClickListener(this)
        InitFlow()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btnFacebook) {
            LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "email"));
        }
    }

    fun InitFlow() {
        if (AccessToken.getCurrentAccessToken() != null) {
            (activity as WelcomeActivity).gotoNextActivity()
            return;
        }
    }

}
