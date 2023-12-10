package com.jlp.sampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jlp.feature_product_list.ui.ProductListScreen
import com.jlp.sampleapp.util.SCREEN_NAME_PRODUCT_LIST
import com.jlp.sampleapp.util.makeSystemStatusBarTransparent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        makeSystemStatusBarTransparent()
        setContent {
            AppContent()
        }
    }
}

@Composable
fun AppContent(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SCREEN_NAME_PRODUCT_LIST) {

        composable(SCREEN_NAME_PRODUCT_LIST) {
            ProductListScreen()
        }
    }

}