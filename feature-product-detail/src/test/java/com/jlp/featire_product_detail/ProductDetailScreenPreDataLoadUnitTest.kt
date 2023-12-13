package com.jlp.featire_product_detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.jlp.core.util.CommonUtil
import com.jlp.featire_product_detail.ui.ProductDetailScreen
import com.jlp.featire_product_detail.ui.ProductDetailViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class ProductDetailScreenPreDataLoadUnitTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    private lateinit var productDetailViewModel: ProductDetailViewModel

    @Mock
    private lateinit var commonUtil: CommonUtil

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    /*@Test
    fun `check loader is displayed`() {

        Mockito.`when`(productDetailViewModel.uiState).thenReturn(
            MutableStateFlow(
                ProductListScreenUiState(loading = true)
            )
        )
        setContent()
        composeTestRule.onNodeWithTag("productListLoader").assertIsDisplayed()
    }*/

    @Test
    fun `check product title is displayed`() {
        setContent()
        composeTestRule.onNodeWithTag("productDetailTitle").assertIsDisplayed()
    }

    @Test
    fun `check back button is displayed`() {
        setContent()
        composeTestRule.onNodeWithTag("productDetailBackButton").assertIsDisplayed()
    }

    private fun setContent(viewModel: ProductDetailViewModel = productDetailViewModel) {

        composeTestRule.setContent {
            ProductDetailScreen(viewModel)
        }
    }
}