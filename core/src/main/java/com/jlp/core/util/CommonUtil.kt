package com.jlp.core.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import java.io.File
import javax.inject.Inject

/**
 * Class for holding common methods.
 */
class CommonUtil @Inject constructor() {

    private val GENY_FILES = arrayOf(
        "/dev/socket/genyd",
        "/dev/socket/baseband_genyd"
    )
    private val PIPES = arrayOf(
        "/dev/socket/qemud",
        "/dev/qemu_pipe"
    )
    private val X86_FILES = arrayOf(
        "ueventd.android_x86.rc",
        "x86.prop",
        "ueventd.ttVM_x86.rc",
        "init.ttVM_x86.rc",
        "fstab.ttVM_x86",
        "fstab.vbox86",
        "init.vbox86.rc",
        "ueventd.vbox86.rc"
    )
    private val ANDY_FILES = arrayOf(
        "fstab.andy",
        "ueventd.andy.rc"
    )
    private val NOX_FILES = arrayOf(
        "fstab.nox",
        "init.nox.rc",
        "ueventd.nox.rc"
    )

    /**
     * Method for checking if device is connected to internet.
     */
    fun isInternetConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

     fun isEmulator(): Boolean {
        return (Build.MANUFACTURER.contains("Genymotion")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.lowercase().contains("droid4x")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.HARDWARE == "goldfish"
                || Build.HARDWARE == "vbox86"
                || Build.HARDWARE.lowercase().contains("nox")
                || Build.FINGERPRINT.startsWith("generic")
                || Build.PRODUCT == "sdk"
                || Build.PRODUCT == "google_sdk"
                || Build.PRODUCT == "sdk_x86"
                || Build.PRODUCT == "vbox86p"
                || Build.PRODUCT.lowercase().contains("nox")
                || Build.BOARD.lowercase().contains("nox")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")))||checkEmulatorFiles()
    }

    private fun checkFiles(targets: Array<String>): Boolean {
        for (pipe in targets) {
            val file = File(pipe)
            if (file.exists()) {
                return true
            }
        }
        return false
    }
    private fun checkEmulatorFiles():Boolean {
        return (checkFiles(GENY_FILES)
                || checkFiles(ANDY_FILES)
                || checkFiles(NOX_FILES)
                || checkFiles(X86_FILES)
                || checkFiles(PIPES))
    }

}