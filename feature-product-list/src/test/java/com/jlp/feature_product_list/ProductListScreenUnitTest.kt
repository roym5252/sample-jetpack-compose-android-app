package com.jlp.feature_product_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.jlp.core.model.Product
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
            ProductListScreen(listOf(Product("Product 1","","")))
        }
        composeTestRule.onNodeWithTag("productList").assertIsDisplayed()
    }

    @Test
    fun `check product image is displayed on grid`() {
        // Start the app
        composeTestRule.setContent {
            ProductListScreen(listOf(Product("Product 1",null,"")))
        }
        composeTestRule.onNodeWithTag("productImage").assertIsDisplayed()
    }

    @Test
    fun `check product name is displayed on grid`() {
        // Start the app
        composeTestRule.setContent {
            ProductListScreen(listOf(Product("Product 1",null,"")))
        }
        composeTestRule.onNodeWithTag("productName").assertIsDisplayed()
    }

}