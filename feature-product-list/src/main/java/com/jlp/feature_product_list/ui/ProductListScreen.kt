package com.jlp.feature_product_list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jlp.core.ui.compose.CustomProgressLoader
import com.jlp.core.ui.compose.InfoMessageAndReload
import com.jlp.core.ui.theme.CustomColor
import com.jlp.feature_product_list.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    lazyListState:LazyGridState,
    productListScreenViewModel: ProductListScreenViewModel = hiltViewModel(),
    onProductClickEvent: (productId: Long, productTitle: String) -> Unit
) {

    val productListUiState by productListScreenViewModel.uiState.collectAsState()

    DisposableEffect(key1 = productListScreenViewModel) {
        productListScreenViewModel.onStart()
        onDispose { productListScreenViewModel.onStop() }
    }

    Box(
        modifier = Modifier
            .background(color = CustomColor.Light_Grey)
            .fillMaxSize()
    ) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        TitleAndSubTitle(productListUiState)
                    }, colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = CustomColor.Light_Grey,
                    ), modifier = Modifier.testTag("productListToolBar")
                )
            },
        ) { innerPadding ->

            Box(
                Modifier
                    .padding(top = 8.dp)
                    .background(CustomColor.Light_Grey)
            ) {

                Column(modifier = Modifier.padding(innerPadding)) {

                    if (productListUiState.loading) {
                        CustomProgressLoader(
                            Modifier.testTag("productListLoader"), stringResource(
                                id = R.string.loading
                            )
                        )
                    } else if (productListUiState.infoMessage != null) {
                        InfoMessageAndReload(stringResource(id = productListUiState.infoMessage!!)) {
                            productListScreenViewModel.onStart()
                        }

                    } else if (productListUiState.products.isNullOrEmpty()) {
                        InfoMessageAndReload(stringResource(id = R.string.no_dishwashers_available)) {
                            productListScreenViewModel.onStart()
                        }
                    } else {

                        productListUiState.products?.let {

                            LazyVerticalGrid(state = lazyListState, columns = GridCells.Adaptive(200.dp),
                                modifier = Modifier.testTag("productList"),
                                content = {

                                    items(it, key = { it.id }) { product ->
                                        ProductGridItem(product = product, onProductClickEvent)
                                    }
                                })
                        }

                    }
                }
            }

        }

    }

}

@Composable
private fun TitleAndSubTitle(
    productListUiState: ProductListScreenUiState
) {
    Column(modifier = Modifier.semantics(mergeDescendants = true) {}) {

        Text(
            text = stringResource(R.string.production_list_title),
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight(800),
                fontFamily = FontFamily(Font(com.jlp.core.R.font.montserrat_bold)),
                color = Color.Black,

                ),
            modifier = Modifier.testTag("productListTitle")
        )

        val subTitle = if (productListUiState.loading) {
            stringResource(R.string.loading)
        } else if (productListUiState.products == null) {
            stringResource(R.string.subtitle_error_text)
        } else {

            if (productListUiState.products.size == 1) {
                "${productListUiState.products.size} ${stringResource(R.string.product_found)}"
            } else {
                "${productListUiState.products.size} ${stringResource(R.string.products_found)}"
            }
        }

        Text(
            text = subTitle, maxLines = 1, overflow = TextOverflow.Ellipsis, style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(com.jlp.core.R.font.montserrat_light)),
                fontWeight = FontWeight(400),
                color = Color.Black,

                ), modifier = Modifier.testTag("productListSubTitle")
        )
    }
}


