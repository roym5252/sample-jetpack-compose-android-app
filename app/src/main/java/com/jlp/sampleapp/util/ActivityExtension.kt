package com.jlp.sampleapp.util

import android.app.Activity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.jlp.sampleapp.R

fun Activity.makeSystemStatusBarTransparent() {

    this.let {

        if (!it.isFinishing) {
            val window = this.window
            val decorView = window.decorView
            val wic = WindowInsetsControllerCompat(window, decorView)
            wic.isAppearanceLightStatusBars = true
            window.statusBarColor = ContextCompat.getColor(this,R.color.grey)
        }

    }

}
