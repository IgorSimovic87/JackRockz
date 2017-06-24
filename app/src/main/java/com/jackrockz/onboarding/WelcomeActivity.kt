package com.jackrockz.onboarding

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat.startActivity
import android.support.v4.view.GravityCompat
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.jackrockz.MyApplication
import com.jackrockz.R
import com.jackrockz.api.UserModel
import com.jackrockz.commons.RxBaseActivity
import com.jackrockz.onboarding.fragments.SelectCountryFragment
import com.jackrockz.onboarding.fragments.WelcomeFragment
import com.jackrockz.root.MainActivity
import com.jackrockz.utils.GlobalConstants
import com.jackrockz.utils.Utils
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class WelcomeActivity : RxBaseActivity() {
    var callbackManager = CallbackManager.Factory.create()
    var onBackPressedListener: MainActivity.OnBackPressedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        InitFlow()
    }

    fun InitFlow() {
        if (AccessToken.getCurrentAccessToken() != null) {
            ProcessToken(AccessToken.getCurrentAccessToken().token, true)
            return
        }
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onCancel() {
                Utils.hideLoading()
            }

            override fun onError(p0: FacebookException?) {
                Utils.hideLoading()
                Utils.showToast(this@WelcomeActivity, "Unfortunately facebook login failed.")
            }

            override fun onSuccess(p0: LoginResult?) {
                if (!subscriptions.hasSubscriptions()) {
                    subscriptions = CompositeSubscription()
                }
                ProcessToken(p0!!.accessToken.token)
            }
        })

        changeFragment(WelcomeFragment())
    }

    fun ProcessToken(facebook_access_token: String, isLogged: Boolean = false) {
        val subscription = apiManager.getToken(facebook_access_token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        { token ->
                            MyApplication.instance.accessToken = token

                            Utils.hideLoading()
                            if (isLogged) {
                                gotoNextActivity()
                            } else {
                                changeFragment(SelectCountryFragment())
                            }
                        },
                        { e ->
                            Utils.hideLoading()
                            Utils.showToast(this@WelcomeActivity, "Unfortunately facebook login failed.")
                        }
                )

        subscriptions.add(subscription)
    }

    fun changeFragment(f: Fragment, cleanStack: Boolean = false) {
        val ft = supportFragmentManager.beginTransaction()
        if (cleanStack) {
            clearBackStack()
        }
        ft.setCustomAnimations(
                R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit)
        ft.add(R.id.activity_base_content, f)
        ft.addToBackStack(null)
        ft.commit()
    }

    fun clearBackStack() {
        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    fun gotoNextActivity() {
        MyApplication.instance.currentUser = Utils.loadObject(GlobalConstants.PREFS_USER, UserModel::class.java)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        if (onBackPressedListener != null) {
            onBackPressedListener!!.doBack()
            return
        }

        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}
