package com.jlp.feature_product_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.jlp.core.ui.theme.JLPSampleAppTheme
import com.jlp.feature_product_list.ui.ProductListScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner::class)
class ProductListScreenUnitTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `check product grid is displayed`() {
        // Start the app
        composeTestRule.setContent {
            ProductListScreen()
        }
        composeTestRule.onNodeWithTag("productList").assertIsDisplayed()
    }
}