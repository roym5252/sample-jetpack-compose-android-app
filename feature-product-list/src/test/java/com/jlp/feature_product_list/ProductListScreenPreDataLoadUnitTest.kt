package com.jlp.feature_product_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.jlp.core.model.Product
import com.jlp.feature_product_list.ui.ProductListScreen
import com.jlp.feature_product_list.ui.ProductListScreenUiState
import com.jlp.feature_product_list.ui.ProductListScreenViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest= Config.NONE)
@RunWith(RobolectricTestRunner::class)
class ProductListScreenPreDataLoadUnitTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    private lateinit var productListScreenViewModel: ProductListScreenViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `check loader is displayed`() {

        Mockito.`when`(productListScreenViewModel.uiState).thenReturn(
            MutableStateFlow(
                ProductListScreenUiState(loading = true)
            )
        )
        setContent()
        composeTestRule.onNodeWithTag("productListLoader").assertIsDisplayed()
    }

    @Test
    fun `check error message and reload icon are displayed when error occurs`() {

        Mockito.`when`(productListScreenViewModel.uiState).thenReturn(
            MutableStateFlow(
                ProductListScreenUiState(loading = false, errorMessage = "Unexpected error occurred.")
            )
        )
        setContent()
        composeTestRule.onNodeWithTag("errorMessage").assertIsDisplayed()
        composeTestRule.onNodeWithTag("reloadIcon").assertIsDisplayed()
    }


    private fun setContent() {

        composeTestRule.setContent {
            ProductListScreen(
                listOf(Product("Product 1", null, "£100.00")),
                productListScreenViewModel
            )
        }
    }
}