package com.jlp.sampleapp

import android.app.Application
import com.jlp.core.util.PrefUtil
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.Forest.plant
import javax.inject.Inject


@HiltAndroidApp
class JLPApp: Application() {

    @Inject
    lateinit var prefUtil: PrefUtil

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }

        /*if (CommonUtil().isEmulator()||RootBeer(this).isRooted){
            exitProcess(0)
        }else{
            prefUtil.saveString("api_key",BuildConfig.API_KEY)
        }*/

        prefUtil.saveString("api_key",BuildConfig.API_KEY)

    }
}