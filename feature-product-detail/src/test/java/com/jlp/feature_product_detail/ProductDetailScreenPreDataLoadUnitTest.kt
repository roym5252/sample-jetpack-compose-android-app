package com.jlp.feature_product_detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.test.core.app.ApplicationProvider
import com.jlp.core.domain.GetProductDetailUseCase
import com.jlp.core.model.ProductDetail
import com.jlp.core.util.CommonUtil
import com.jlp.core.util.TaskResult
import com.jlp.feature_product_detail.ui.ProductDetailScreen
import com.jlp.feature_product_detail.ui.ProductDetailScreenUiState
import com.jlp.feature_product_detail.ui.ProductDetailViewModel
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
class ProductDetailScreenPreDataLoadUnitTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    private lateinit var productDetailViewModel: ProductDetailViewModel

    @Mock
    private lateinit var commonUtil: CommonUtil

    @Mock
    private lateinit var navigationHostController: NavHostController

    @Mock
    private lateinit var getProductDetailUseCase: GetProductDetailUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        val testProductDetail=ProductDetail(1L,"100.00",
            listOf("test_image_url"),"test_code","test_product_info",
            listOf(Pair("test_feature_1","test_feature_value_1")),"test_additional_included_services","test_additional_included_services")

        getProductDetailUseCase.stub {
            onBlocking { invoke(Mockito.anyLong()) }.doReturn(TaskResult.Success(testProductDetail))
        }

    }

    @Test
    fun `check product title is displayed`() {
        Mockito.`when`(productDetailViewModel.uiState).thenReturn(
            MutableStateFlow(
                ProductDetailScreenUiState(
                    loading = true
                )
            )
        )
        setContent()
        composeTestRule.onNodeWithTag("productDetailTitle").assertIsDisplayed()
    }

    @Test
    fun `check back button is displayed`() {
        Mockito.`when`(productDetailViewModel.uiState).thenReturn(
            MutableStateFlow(
                ProductDetailScreenUiState(
                    loading = true
                )
            )
        )
        setContent()
        composeTestRule.onNodeWithTag("productDetailBackButton").assertIsDisplayed()
    }

    @Test
    fun `check loader is displayed`() {
        Mockito.`when`(productDetailViewModel.uiState).thenReturn(
            MutableStateFlow(
                ProductDetailScreenUiState(
                    loading = true
                )
            )
        )
        setContent()
        composeTestRule.onNodeWithTag("productDetailLoader").assertIsDisplayed()
    }

    @Test
    fun `check not connectivity message and reload icon are displayed when internet is not connected`() {

        Mockito.`when`(commonUtil.isInternetConnected(ApplicationProvider.getApplicationContext()))
            .thenReturn(false)
        Mockito.`when`(productDetailViewModel.commonUtil).thenReturn(commonUtil)

        Mockito.`when`(productDetailViewModel.uiState).thenReturn(
            MutableStateFlow(
                ProductDetailScreenUiState(loading = false)
            )
        )
        setContent()
        composeTestRule.onNodeWithTag("errorMessage").assertIsDisplayed()
        composeTestRule.onNodeWithText("No internet connectivity. Tap to retry.")
        composeTestRule.onNodeWithTag("reloadIcon").assertIsDisplayed()
    }

    @Test
    fun `check reload after error info display is working`() {

        val viewModel =
            ProductDetailViewModel(commonUtil, ApplicationProvider.getApplicationContext(),getProductDetailUseCase)
        Mockito.`when`(commonUtil.isInternetConnected(ApplicationProvider.getApplicationContext()))
            .thenReturn(false)
        setContent(viewModel = viewModel)
        composeTestRule.onNodeWithTag("errorMessage").assertIsDisplayed()
        composeTestRule.onNodeWithText("No internet connectivity. Tap to retry.")
        composeTestRule.onNodeWithTag("reloadIcon").assertIsDisplayed()
        Mockito.`when`(commonUtil.isInternetConnected(ApplicationProvider.getApplicationContext()))
            .thenReturn(true)
        composeTestRule.onNodeWithTag("reloadIcon").performClick()
        composeTestRule.onNodeWithTag("reloadIcon").assertDoesNotExist()
        composeTestRule.onNodeWithTag("errorMessage").assertDoesNotExist()
        composeTestRule.onNodeWithTag("productDetailList").assertIsDisplayed()
    }

    private fun setContent(viewModel: ProductDetailViewModel = productDetailViewModel) {

        Mockito.`when`(commonUtil.isInternetConnected(ApplicationProvider.getApplicationContext())).thenReturn(false)
        Mockito.`when`(productDetailViewModel.commonUtil).thenReturn(commonUtil)
        composeTestRule.setContent {
            ProductDetailScreen(1L, "Test Title", navigationHostController,viewModel)
        }
    }
}