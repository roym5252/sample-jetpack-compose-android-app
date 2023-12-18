package com.jlp.sampleapp

import android.app.Application
import com.jlp.core.util.CommonUtil
import com.jlp.core.util.LogUtil
import com.jlp.core.util.PrefUtil
import com.scottyab.rootbeer.RootBeer
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import kotlin.system.exitProcess

@HiltAndroidApp
class JLPApp: Application() {

    @Inject
    lateinit var prefUtil: PrefUtil

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            LogUtil.init()
        }

        //Checking if build is release version.
        if(BuildConfig.BUILD_TYPE.contentEquals("release")&&!BuildConfig.DEBUG){

            //Checking if app is running on emulator or device is rooted
            if (CommonUtil().isEmulator()|| RootBeer(this).isRooted){

                //Exiting app
                exitProcess(0)
            }else{
                prefUtil.saveString("api_key",BuildConfig.API_KEY)
            }

        }else{
            prefUtil.saveString("api_key",BuildConfig.API_KEY)
        }
    }
}