package com.jlp.sampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jlp.core.util.LogUtil
import com.jlp.feature_product_detail.ui.ProductDetailScreen
import com.jlp.feature_product_list.ui.ProductListScreen
import com.jlp.sampleapp.util.ARGUMENT_PRODUCT_ID
import com.jlp.sampleapp.util.ARGUMENT_PRODUCT_TITLE
import com.jlp.sampleapp.util.SCREEN_NAME_PRODUCT_DETAIL
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
fun AppContent() {

    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = SCREEN_NAME_PRODUCT_LIST) {

        composable(SCREEN_NAME_PRODUCT_LIST) {

            ProductListScreen(){ productId: Long, productTitle: String ->
                LogUtil.d("Product ID: $productId and Title:$productTitle")
                navController.navigate("$SCREEN_NAME_PRODUCT_DETAIL/${productId}/${productTitle}")
            }
        }

        composable(
            route = "$SCREEN_NAME_PRODUCT_DETAIL/{$ARGUMENT_PRODUCT_ID}/{$ARGUMENT_PRODUCT_TITLE}",
            arguments = listOf(navArgument(ARGUMENT_PRODUCT_ID) {
                type = NavType.LongType
            }, navArgument(ARGUMENT_PRODUCT_TITLE) {
                type = NavType.StringType
            })

        ) {
            val productId = it.arguments?.getLong(ARGUMENT_PRODUCT_ID)
            val productTitle = it.arguments?.getString(ARGUMENT_PRODUCT_TITLE)

            if (productId != null) {
                ProductDetailScreen(productId, productTitle, navController = navController)
            }

        }
    }

}