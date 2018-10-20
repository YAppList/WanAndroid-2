package com.sqsong.wanandroid.ui.splash.mvp

import android.widget.TextView
import androidx.annotation.IdRes
import com.sqsong.wanandroid.mvp.IView

interface SplashContract {

    interface View : IView {
        fun getTimerTextView(): TextView
        fun showBackgroundImage(@IdRes resId: Int)
        fun startNewActivity(clazz: Class<*>)
    }

}