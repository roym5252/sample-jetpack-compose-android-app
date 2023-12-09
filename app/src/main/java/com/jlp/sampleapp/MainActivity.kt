package com.jlp.sampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.jlp.core.model.Product
import com.jlp.feature_product_list.ui.ProductListScreen
import com.jlp.sampleapp.util.makeSystemStatusBarTransparent

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
    ProductListScreen(listOf(Product("Product 1","ssd",""),
        Product("Product 2","ssd","")))
}