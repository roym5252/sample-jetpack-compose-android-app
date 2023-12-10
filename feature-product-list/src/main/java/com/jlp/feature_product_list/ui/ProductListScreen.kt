package com.jlp.feature_product_list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jlp.core.R
import com.jlp.core.model.Product
import com.jlp.core.ui.compose.CustomProgressLoader
import com.jlp.core.ui.compose.InfoMessageAndReload
import com.jlp.core.ui.theme.CustomColor

@Composable
fun ProductListScreen(productListScreenViewModel: ProductListScreenViewModel = hiltViewModel()) {

    val productListUiState by productListScreenViewModel.uiState.collectAsState()

    DisposableEffect(key1 = productListScreenViewModel) {
        productListScreenViewModel.onStart()
        onDispose { productListScreenViewModel.onStop() }
    }

    Box(
        modifier = Modifier
            .background(color = CustomColor.Grey)
            .fillMaxSize()
    ) {

        Column {

            TitleAndSubTitle(productListUiState, productListUiState.products)

            if (productListUiState.loading) {
                CustomProgressLoader(Modifier.testTag("productListLoader"))
            } else if (!productListUiState.infoMessage.isNullOrBlank()) {
                InfoMessageAndReload("Error Message.")
            } else if (productListUiState.products.isNullOrEmpty()) {
                InfoMessageAndReload("No dishwashers available.")
            } else {

                productListUiState.products?.let {

                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(200.dp),
                        Modifier.testTag("productList"),
                        content = {

                            items(it) { product ->
                                ProductGridItem(product = product)
                            }
                        })
                }

            }
        }

    }

}

@Composable
private fun TitleAndSubTitle(
    productListUiState: ProductListScreenUiState,
    products: List<Product>?
) {
    Column(Modifier.padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)) {

        Text(
            text = "Dishwasher",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight(800),
                fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                color = Color(0xFF000000),

                ),
            modifier = Modifier
                .testTag("productListTitle")
        )

        val subTitle = if (productListUiState.loading) {
            "Loading..."
        } else if (products == null) {
            "--"
        } else {
            "${products.size} products found"
        }

        Text(
            text = subTitle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_light)),
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),

                ),
            modifier = Modifier.testTag("productListSubTitle")
        )
    }
}


