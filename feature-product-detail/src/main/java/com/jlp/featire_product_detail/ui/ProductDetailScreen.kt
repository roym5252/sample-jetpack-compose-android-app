package com.jlp.featire_product_detail.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProductDetailScreen(productDetailViewModel: ProductDetailViewModel= hiltViewModel()){

    Text(text = "Detail Title", modifier = Modifier.testTag("productDetailTitle"))
}