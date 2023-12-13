package com.jlp.feature_product_list

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
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
        Mockito.`when`(productListScreenViewModel.uiState).thenReturn(MutableStateFlow(ProductListScreenUiState(loading = false, products = listOf(Product(0L,"Product 1",null,"£100.00")))))

        composeTestRule.setContent {
            ProductListScreen(productListScreenViewModel)
        }
    }

    @Test
    fun `check title is shown`() {
        composeTestRule.onNodeWithTag("productListTitle",useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun `check subtitle is shown`() {
        composeTestRule.onNodeWithTag("productListSubTitle",useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun `check subtitle shows correct product count`() {
        composeTestRule.onNodeWithTag("productListSubTitle",useUnmergedTree = true).assertTextContains("1", substring = true)
    }

    @Test
    fun `check product grid is displayed`() {
        composeTestRule.onNodeWithTag("productList").assertIsDisplayed()
    }

    @Test
    fun `check product image is displayed on grid`() {
        composeTestRule.onNodeWithTag("productImage",useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun `check product name is displayed on grid`() {
        composeTestRule.onNodeWithTag("productName",useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun `check product price is displayed on grid`() {
        composeTestRule.onNodeWithTag("productGridItem",useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun `check product price is displaying with two decimal places on grid`() {
        composeTestRule.onNodeWithText("£100.00").assertIsDisplayed()
    }

    @Test
    fun `check product name on grid displays same as passed`() {
        composeTestRule.onNodeWithTag("productName",useUnmergedTree = true).assertTextEquals("Product 1")
    }

    @Test
    fun `check product price on grid displays same as passed`() {
        composeTestRule.onNodeWithTag("productPrice",useUnmergedTree = true).assertTextEquals("£100.00")
    }

    @Test
    fun `check click label is set correctly for product item in product list grid`() {
        composeTestRule.onNodeWithTag("productGridItem").assert(
            SemanticsMatcher("onClickLabel is set correctly for product item") {
                it.config.getOrNull(SemanticsActions.OnClick)?.label == "Tap to open detail screen."
            }
        )
    }
}