package com.jlp.feature_product_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import com.jlp.core.model.Product
import com.jlp.core.util.CommonUtil
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

@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner::class)
class ProductListScreenPostDataLoadUnitTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    private lateinit var productListScreenViewModel: ProductListScreenViewModel

    @Mock
    private lateinit var commonUtil: CommonUtil

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        Mockito.`when`(commonUtil.isInternetConnected(ApplicationProvider.getApplicationContext())).thenReturn(true)
        Mockito.`when`(productListScreenViewModel.commonUtil).thenReturn(commonUtil)
        Mockito.`when`(productListScreenViewModel.uiState).thenReturn(MutableStateFlow(ProductListScreenUiState(loading = false, products = listOf(Product("Product 1",null,"£100.00")))))

        composeTestRule.setContent {
            ProductListScreen(productListScreenViewModel)
        }
    }

    @Test
    fun `check title is shown`() {
        composeTestRule.onNodeWithTag("productListTitle").assertIsDisplayed()
    }

    @Test
    fun `check subtitle is shown`() {
        composeTestRule.onNodeWithTag("productListSubTitle").assertIsDisplayed()
    }

    @Test
    fun `check subtitle shows correct product count`() {
        composeTestRule.onNodeWithTag("productListSubTitle").assertTextContains("1", substring = true)
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

    @Test
    fun `check product name on grid displays same as passed`() {
        composeTestRule.onNodeWithTag("productName").assertTextEquals("Product 1")
    }

    @Test
    fun `check product price on grid displays same as passed`() {
        composeTestRule.onNodeWithTag("productPrice").assertTextEquals("£100.00")
    }
}