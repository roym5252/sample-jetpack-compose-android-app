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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jlp.core.model.Product
import com.jlp.core.ui.theme.CustomColor
import com.jlp.feature_product_list.R

@Composable
fun ProductGridItem(product: Product,onProductClickEvent:(productId:Long,productTitle:String)->Unit) {

    Box(
        Modifier
            .testTag("productGridItem")
            .border(
                width = 1.dp,
                color = CustomColor.Light_Grey,
            )
            .clickable(onClickLabel = stringResource(id = R.string.tap_to_open_detail_screen), onClick = {
                onProductClickEvent(product.id,product.title)
            })
            .padding(1.dp)
            .background(color = Color.White)
            .semantics(mergeDescendants = true) {},
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = if (product.image?.isNotEmpty() == true) product.image else com.jlp.core.R.drawable.ic_placeholder,
                contentDescription = null,
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .testTag("productImage")
                    .sizeIn(minHeight = 300.dp),
                contentScale = ContentScale.Fit,
                placeholder = painterResource(id = com.jlp.core.R.drawable.ic_placeholder)
            )

            Text(
                text = product.title,
                Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth()
                    .testTag("productName"),
                textAlign = TextAlign.Start,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 25.sp,
                    fontFamily = FontFamily(Font(com.jlp.core.R.font.montserrat_medium)),
                    fontWeight = FontWeight(400),
                    color = CustomColor.Medium_Grey
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
            )

            Text(
                text = product.price,
                Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth()
                    .testTag("productPrice"),
                textAlign = TextAlign.Start,
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 16.sp,
                    fontFamily = FontFamily(Font(com.jlp.core.R.font.montserrat_bold)),
                    fontWeight = FontWeight(800),
                    color = CustomColor.Medium_Grey
                )
            )
        }
    }
}