package com.jackrockz.onboarding

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.jackrockz.R
import com.jackrockz.onboarding.fragments.SelectCountryFragment
import com.jackrockz.onboarding.fragments.WelcomeFragment
import com.jackrockz.root.MainActivity

class WelcomeActivity : AppCompatActivity() {
    var callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        InitFlow()
    }

    fun InitFlow() {
        if (AccessToken.getCurrentAccessToken() != null) {
            gotoNextActivity()
            return;
        }
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onCancel() {
            }

            override fun onError(p0: FacebookException?) {
                Toast.makeText(this@WelcomeActivity, "Unfortunately facebook login failed.", Toast.LENGTH_LONG).show()
            }

            override fun onSuccess(p0: LoginResult?) {
                changeFragment(SelectCountryFragment())
            }
        })

        changeFragment(WelcomeFragment())
    }

    fun changeFragment(f: Fragment, cleanStack: Boolean = false) {
        val ft = supportFragmentManager.beginTransaction();
        if (cleanStack) {
            clearBackStack();
        }
        ft.setCustomAnimations(
                R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit);
        ft.replace(R.id.activity_base_content, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    fun clearBackStack() {
        val manager = supportFragmentManager;
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    fun gotoNextActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager;
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack();
        } else {
            finish();
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}
