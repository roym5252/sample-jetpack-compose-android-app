package com.jlp.featire_product_detail.ui

import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.jlp.featire_product_detail.R

@Composable
fun ProductDetailScreen(productDetailViewModel: ProductDetailViewModel= hiltViewModel()){

    Text(text = "Detail Title", modifier = Modifier.testTag("productDetailTitle"))
    Image(painter = painterResource(id = com.jlp.core.R.drawable.ic_placeholder), modifier = Modifier.testTag("productDetailBackButton"), contentDescription = "Back button")
}