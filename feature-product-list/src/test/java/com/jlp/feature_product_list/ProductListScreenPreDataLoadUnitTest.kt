package com.jlp.feature_product_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import com.jlp.core.domain.GetProductsUseCase
import com.jlp.core.model.Product
import com.jlp.core.util.CommonUtil
import com.jlp.core.util.TaskResult
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
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.stub
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class ProductListScreenPreDataLoadUnitTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    private lateinit var productListScreenViewModel: ProductListScreenViewModel

    @Mock
    private lateinit var commonUtil: CommonUtil

    @Mock
    private lateinit var getProductsUseCase: GetProductsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        getProductsUseCase.stub {
            onBlocking { invoke() }.doReturn(TaskResult.Success(listOf(Product(0L,"Product 1",null,"£100.00"))))
        }
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
                ProductListScreenUiState(
                    loading = false,
                    infoMessage = R.string.unexpected_error_retry
                )
            )
        )
        setContent()
        composeTestRule.onNodeWithTag("errorMessage").assertIsDisplayed()
        composeTestRule.onNodeWithTag("reloadIcon").assertIsDisplayed()
    }

    @Test
    fun `check not connectivity message and reload icon are displayed when internet is not connected`() {

        Mockito.`when`(commonUtil.isInternetConnected(ApplicationProvider.getApplicationContext())).thenReturn(false)
        Mockito.`when`(productListScreenViewModel.commonUtil).thenReturn(commonUtil)

        Mockito.`when`(productListScreenViewModel.uiState).thenReturn(
            MutableStateFlow(
                ProductListScreenUiState(loading = false)
            )
        )
        setContent()
        composeTestRule.onNodeWithTag("errorMessage").assertIsDisplayed()
        composeTestRule.onNodeWithText("No internet connectivity. Tap to retry.")
        composeTestRule.onNodeWithTag("reloadIcon").assertIsDisplayed()
    }

    @Test
    fun `check reload after error info display is working`() {

        val viewModel = ProductListScreenViewModel(ApplicationProvider.getApplicationContext(),commonUtil,getProductsUseCase)
        Mockito.`when`(commonUtil.isInternetConnected(ApplicationProvider.getApplicationContext())).thenReturn(false)
        setContent(viewModel = viewModel)
        composeTestRule.onNodeWithTag("errorMessage").assertIsDisplayed()
        composeTestRule.onNodeWithText("No internet connectivity. Tap to retry.")
        composeTestRule.onNodeWithTag("reloadIcon").assertIsDisplayed()
        Mockito.`when`(commonUtil.isInternetConnected(ApplicationProvider.getApplicationContext())).thenReturn(true)
        composeTestRule.onNodeWithTag("reloadIcon").performClick()
        composeTestRule.onNodeWithTag("reloadIcon").assertDoesNotExist()
        composeTestRule.onNodeWithTag("errorMessage").assertDoesNotExist()
        composeTestRule.onNodeWithTag("productList").assertIsDisplayed()
    }

    private fun setContent(viewModel:ProductListScreenViewModel = productListScreenViewModel) {

        composeTestRule.setContent {
            ProductListScreen(viewModel){

            }
        }
    }
}