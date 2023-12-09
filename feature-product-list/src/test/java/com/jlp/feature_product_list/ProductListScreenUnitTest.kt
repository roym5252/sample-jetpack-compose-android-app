package com.jlp.feature_product_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.jlp.core.model.Product
import com.jlp.feature_product_list.ui.ProductListScreen
import org.junit.Before
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

    @Before
    fun setUp(){

        composeTestRule.setContent {
            ProductListScreen(listOf(Product("Product 1",null,"£100.00")))
        }
    }

    @Test
    fun `check screen title is shown`() {
        composeTestRule.onNodeWithTag("productListTitle").assertIsDisplayed()
    }

    @Test
    fun `check product grid is displayed`() {
        composeTestRule.onNodeWithTag("productList").assertIsDisplayed()
    }

    @Test
    fun `check product image is displayed on grid`() {
        composeTestRule.onNodeWithTag("productImage").assertIsDisplayed()
    }

    @Test
    fun `check product name is displayed on grid`() {
        composeTestRule.onNodeWithTag("productName").assertIsDisplayed()
    }

    @Test
    fun `check product price is displayed on grid`() {
        composeTestRule.onNodeWithTag("productPrice").assertIsDisplayed()
    }

    @Test
    fun `check product price is displaying with two decimal places on grid`() {
        composeTestRule.onNodeWithText("£100.00").assertIsDisplayed()
    }

}