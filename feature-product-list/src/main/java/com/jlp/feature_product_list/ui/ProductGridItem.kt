package com.jlp.feature_product_list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jlp.core.model.Product
import com.jlp.feature_product_list.R

@Composable
fun ProductGridItem(product: Product) {

    Box(
        Modifier
            .border(
                width = 1.dp,
                color = Color(0xFFF2F2F2),
                shape = RoundedCornerShape(size = 15.dp)
            )
            .padding(1.dp)
            .width(160.dp)
            .height(215.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 15.dp))
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = if (product.image?.isNotEmpty() == true) "https://static.standard.co.uk/2023/06/27/10/android-logo.jpg?width=1200" else R.drawable.home,
                contentDescription = "$product image",
                modifier = Modifier
                    .width(200.dp)
                    .height(120.dp)
                    .testTag("productImage")
                    .sizeIn(minHeight = 300.dp)
                    .clipToBounds()
                    .clickable(onClickLabel = "Test") {

                    },
                contentScale = ContentScale.Crop,
            )

            Text(
                text = "Dishwasher",
                Modifier.padding(top = 4.dp).fillMaxWidth().testTag("productName"), textAlign = TextAlign.Start,
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF565656)
                )
            )

            Text(
                text = product.price,
                Modifier.padding(top = 4.dp).fillMaxWidth().testTag("productPrice"), textAlign = TextAlign.Start,
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(800),
                    color = Color(0xFF565656)
                )
            )
        }
    }
}