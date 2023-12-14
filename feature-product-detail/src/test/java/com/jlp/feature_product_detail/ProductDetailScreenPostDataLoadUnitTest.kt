package com.jlp.feature_product_detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.NavHostController
import androidx.test.core.app.ApplicationProvider
import com.jlp.core.model.Product
import com.jlp.core.util.CommonUtil
import com.jlp.feature_product_detail.ui.ProductDetailScreenUiState
import com.jlp.feature_product_detail.ui.ProductDetailViewModel
import com.jlp.feature_product_detail.ui.compose.ProductDetailScreen
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
class ProductDetailScreenPostDataLoadUnitTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    private lateinit var productDetailViewModel: ProductDetailViewModel

    @Mock
    private lateinit var commonUtil: CommonUtil

    @Mock
    private lateinit var navHostController: NavHostController

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        Mockito.`when`(commonUtil.isInternetConnected(ApplicationProvider.getApplicationContext())).thenReturn(true)
        Mockito.`when`(productDetailViewModel.commonUtil).thenReturn(commonUtil)

        Mockito.`when`(productDetailViewModel.uiState).thenReturn(
            MutableStateFlow(
                ProductDetailScreenUiState(loading = false, product = Product(1L,"Product 1",null,"£100.00"))
            )
        )

        composeTestRule.setContent {
            ProductDetailScreen(1L, "Test Title",navHostController, productDetailViewModel)
        }
    }

    @Test
    fun `check product detail list is displayed`() {
        composeTestRule.onNodeWithTag("productDetailList").assertIsDisplayed()
    }

    @Test
    fun `check product detail pager is displayed`() {
        composeTestRule.onNodeWithTag("productDetailPager").assertIsDisplayed()
    }

    @Test
    fun `check product detail main image is displayed`() {
        composeTestRule.onNodeWithTag("productDetailImage-0").assertIsDisplayed()
    }

    @Test
    fun `check product detail pager indicator is displayed`() {
        composeTestRule.onNodeWithTag("productDetailPageIndicator").assertIsDisplayed()
    }

    @Test
    fun `check product detail price is displayed`() {
        composeTestRule.onNodeWithTag("productDetailList").performScrollToIndex(1)
        composeTestRule.onNodeWithTag("price").performScrollTo().assertIsDisplayed()
    }

    @Test
    fun `check product detail guaranteeClaimInfo is displayed`() {
        composeTestRule.onNodeWithTag("productDetailList").performScrollToIndex(1)
        composeTestRule.onNodeWithTag("guaranteeClaimInfo").performScrollTo().assertIsDisplayed()
    }

    @Test
    fun `check product detail includedGuaranteeInfo is displayed`() {
        composeTestRule.onNodeWithTag("productDetailList").performScrollToIndex(1)
        composeTestRule.onNodeWithTag("includedGuaranteeInfo").performScrollTo().assertIsDisplayed()
    }

    @Test
    fun `check product information label is displayed`() {
        composeTestRule.onNodeWithTag("productDetailList").performScrollToIndex(2)
        composeTestRule.onNodeWithTag("productInfoLabel").assertIsDisplayed()
    }

    @Test
    fun `check product information text is displayed`() {

        composeTestRule.onNodeWithTag("productDetailList").performScrollToIndex(2)
        composeTestRule.onNodeWithTag("productInfoText").assertIsDisplayed()
    }

    @Test
    fun `check product product specification label is displayed`() {
        composeTestRule.onNodeWithTag("productDetailList").performScrollToIndex(2)
        composeTestRule.onNodeWithTag("productSpecificationLabel").assertIsDisplayed()
    }

    @Test
    fun `check product product specifications are displayed`() {

        composeTestRule.onNodeWithTag("productDetailList").performScrollToIndex(3)
        composeTestRule.onNodeWithTag("FeatureLabel-F1").assertIsDisplayed()
        composeTestRule.onNodeWithTag("FeatureValue-V1").assertIsDisplayed()

        composeTestRule.onNodeWithTag("productDetailList").performScrollToIndex(4)
        composeTestRule.onNodeWithTag("FeatureLabel-F2").assertIsDisplayed()
        composeTestRule.onNodeWithTag("FeatureValue-V2").assertIsDisplayed()
    }

}