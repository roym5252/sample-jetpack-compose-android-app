package com.jlp.sampleapp

import android.app.Application
import com.jlp.core.util.PrefUtil
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class JLPApp: Application() {

    @Inject
    lateinit var prefUtil: PrefUtil

    /*companion object {
        init {

            *//**
             * Loading c file which includes API key.
             *//*
            System.loadLibrary("keys")
        }
    }*/

    private external fun getApiKey(): String

    override fun onCreate() {
        super.onCreate()

        /*if (CommonUtil().isEmulator()||RootBeer(this).isRooted){
            exitProcess(0)
        }else{
            prefUtil.saveString("api_key",getApiKey())
        }*/


        prefUtil.saveString("api_key",BuildConfig.API_KEY)

    }
}