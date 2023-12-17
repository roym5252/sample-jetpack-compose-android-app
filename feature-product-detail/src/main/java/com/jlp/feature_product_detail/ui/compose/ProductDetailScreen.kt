package com.jlp.feature_product_detail.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jlp.core.ui.compose.CustomProgressLoader
import com.jlp.core.ui.compose.InfoMessageAndReload
import com.jlp.core.ui.theme.CustomColor
import com.jlp.featire_product_detail.R
import com.jlp.feature_product_detail.ui.ProductDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: Long,
    productTitle: String?,
    navController: NavHostController,
    productDetailViewModel: ProductDetailViewModel = hiltViewModel()
) {

    val productDetailUiState by productDetailViewModel.uiState.collectAsState()

    DisposableEffect(key1 = productDetailViewModel) {
        productDetailViewModel.onStart(productId)
        onDispose { productDetailViewModel.onStop() }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = productTitle ?: run { "" },
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight(800),
                            fontFamily = FontFamily(Font(com.jlp.core.R.font.montserrat_light)),
                            color = Color(0xFF000000),

                            ),
                        modifier = Modifier.testTag("productDetailTitle"),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CustomColor.Grey,
                ), modifier = Modifier.testTag("productDetailToolBar"),
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.testTag("productDetailBackButton")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_button)
                        )
                    }
                }
            )
        },
    ) { innerPadding ->

        Box(Modifier.padding(innerPadding)) {

            if (productDetailUiState.loading) {

                CustomProgressLoader(
                    modifier = Modifier.testTag("productDetailLoader"),
                    contentDescriptionText = stringResource(
                        id = com.jlp.core.R.string.loading
                    )
                )

            } else if (productDetailUiState.infoMessage != null) {

                InfoMessageAndReload(stringResource(id = productDetailUiState.infoMessage!!)) {
                    productDetailViewModel.onStart(productId)
                }

            } else if (productDetailUiState.productDetail == null) {

                InfoMessageAndReload(stringResource(id = R.string.no_dishwasher_detail_available)) {
                    productDetailViewModel.onStart(productId)
                }

            } else {

                productDetailUiState.productDetail?.let {
                    ProductDetailList(it)
                }
            }
        }
    }
}

