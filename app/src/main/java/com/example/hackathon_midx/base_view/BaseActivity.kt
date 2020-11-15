package com.example.hackathon_midx.base_view

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.hackathon_midx.R
import com.google.android.material.snackbar.Snackbar


abstract class BaseActivity : AppCompatActivity(),
    BaseView {
    companion object {
        const val SNACKBAR_MESSAGE = "snack_bar_message"
        const val SNACKBAR_MSG_CODE_ERROR = 0
        const val SNACKBAR_MSG_CODE_SUCCESS = 1
    }

    var mSnackBar: Snackbar? = null
    private var mAlertDialog: AlertDialog? = null

    protected abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window?.apply {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                statusBarColor = Color.WHITE
            }
        }
        addControls()
        addEvents()
    }

    open fun showSnackBar(errorMsg: String?, messageCode: Int = 0) {
        val textColorRes: Int?
        val bgColorRes: Int?
        when (messageCode) {
            SNACKBAR_MSG_CODE_SUCCESS -> {
                textColorRes = getColorMidX(
                    this,
                    R.color.white
                )
                bgColorRes = getColorMidX(
                    this,
                    R.color.toast_green
                )
            }

            else -> {
                textColorRes = getColorMidX(
                    this,
                    R.color.oxford_blue
                )
                bgColorRes = getColorMidX(
                    this,
                    R.color.toast_yellow
                )
            }
        }

        mSnackBar = makeSnackBar(errorMsg!!, textColorRes, bgColorRes, Snackbar.LENGTH_SHORT)
        mSnackBar?.show()
    }

    fun getColorMidX(context: Context, color: Int): Int {
        return ContextCompat.getColor(context, color)
    }

    private fun makeSnackBar(msg: String, textColor: Int, bgColor: Int, duration: Int): Snackbar? {
        val sBar = Snackbar.make(findViewById(android.R.id.content), msg, duration)
        val view = sBar.view
        view.setBackgroundColor(bgColor)
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        val tv = view.findViewById<TextView>(R.id.snackbar_text)
        tv.textAlignment = View.TEXT_ALIGNMENT_CENTER
        tv.setTextColor(textColor)
        tv.maxLines = 10
        return sBar
    }
//
//    override fun attachBaseContext(newBase: Context) {
//        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
//    }

    fun openFragment(container: Int, fragment: Fragment, name: String) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.screen_enter, R.anim.screen_exit)
            .replace(container, fragment)
            .addToBackStack(name)
            .commitAllowingStateLoss()
    }

    fun initLooper(): Looper? {
        var looper = Looper.myLooper()
        if (looper == null) {
            looper = Looper.getMainLooper()
        }
        return looper
    }

    private fun showLoadingDialog(activity: Activity): AlertDialog? {
        val builder = AlertDialog.Builder(activity)
        val view: View = layoutInflater.inflate(R.layout.progress_dialog, null)
        //ImageView imageLoading = view.findViewById(R.id.loader_iv);
        //Glide.with(this).load(R.drawable.hako_loader).into(imageLoading);
        builder.setCancelable(false)
        builder.setView(view)
        val alertDialog = builder.create()
        alertDialog.show()
        if (alertDialog.window != null) {
            alertDialog.window!!
                .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        return alertDialog
    }

    open fun showLoadings(activity: Activity?) {
        if (activity == null) {
            return
        }
        hideLoadings()
        mAlertDialog = showLoadingDialog(activity)
    }

    open fun hideLoadings() {
        if (mAlertDialog != null && mAlertDialog?.isShowing == true) {
            mAlertDialog?.dismiss()
        }
    }
}