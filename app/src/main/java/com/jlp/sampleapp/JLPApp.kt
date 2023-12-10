package com.jlp.sampleapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JLPApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}