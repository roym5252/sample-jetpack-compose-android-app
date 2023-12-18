package com.jlp.core.util

import timber.log.Timber

/**
 * Wrapper for holding the logging library.
 * This will help to change the logging library without having to make changes in multiple places.
 */
object LogUtil {

    fun init(){
        Timber.plant(Timber.DebugTree())
    }
    fun d(message:String){
        Timber.d(message)
    }

    fun e(message:String){
        Timber.e(message)
    }

    fun i(message:String){
        Timber.i(message)
    }
}