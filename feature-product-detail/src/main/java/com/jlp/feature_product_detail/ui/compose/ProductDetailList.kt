package com.jlp.feature_product_detail.ui.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductDetailList(productDetail: Detail = dummyDetail()) {

    val pagerState = rememberPagerState(pageCount = {
        4
    })

    LazyColumn(content = {

        items(toCell(productDetail)) {

            when (it) {

                is Cell.ProductImage -> {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("productDetailPager")
                        ) { page ->
                            AsyncImage(
                                model = "https://static.standard.co.uk/2023/06/27/10/android-logo.jpg?width=1200",
                                contentDescription = null,
                                modifier = Modifier
                                    .defaultMinSize(minHeight = 100.dp)
                                    .clickable(onClickLabel = "Test") {
                                    }
                                    .testTag("productDetailImage-$page")
                                    .fillMaxWidth(),
                                contentScale = ContentScale.Crop,
                                placeholder = painterResource(id = com.jlp.core.R.drawable.ic_placeholder)
                            )
                        }
                        Row(
                            Modifier
                                .wrapContentHeight()
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                                .testTag("productDetailPageIndicator"),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            repeat(pagerState.pageCount) { iteration ->
                                val color =
                                    if (pagerState.currentPage == iteration) Color.Black else Color.Red
                                Box(
                                    modifier = Modifier
                                        .padding(2.dp)
                                        .clip(CircleShape)
                                        .background(color)
                                        .size(12.dp)
                                )
                            }
                        }
                    }

                }

                is Cell.ProductPriceInfo ->{

                    Column {
                        Text(text = it.price, modifier = Modifier.testTag("price"))
                        Text(text = it.guaranteeClaimInfo,modifier = Modifier.testTag("guaranteeClaimInfo"))
                        Text(text = it.includedGuaranteeInfo,modifier = Modifier.testTag("includedGuaranteeInfo"))
                    }
                }

                is Cell.ProductInfo ->{

                    Column {
                        Text(text = "Product information Label", modifier = Modifier.testTag("productInfoLabel"))
                        Text(text = it.productCode, modifier = Modifier.testTag("productCode"))
                        Text(text = it.productInfoText, modifier = Modifier.testTag("productInfoText"))
                    }
                }

                is Cell.ProductSpecificationLabel ->{
                    Column {
                        Text(text = "Product specification Label", modifier = Modifier.testTag("productSpecificationLabel"))

                    }
                }

                is Cell.ProductSpecification ->{
                    Box(Modifier.padding(8.dp)) {

                        Row(
                            Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = it.feature,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 4.dp).testTag("FeatureLabel-${it.feature}"),
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = it.value, Modifier.weight(1f).testTag("FeatureValue-${it.value}"))
                        }

                    }
                }
            }
        }
    }, modifier = Modifier.testTag("productDetailList"))
}

fun toCell(product: Detail): List<Cell> {

    val cells = mutableListOf<Cell>()
    val productImage = Cell.ProductImage(listOf(product.images[0]))
    val productPriceInfo = Cell.ProductPriceInfo("100.00","Claim an extra 3 years guarantee via redemption","Claim an extra 3 years guarantee via redemption")
    val productInfo = Cell.ProductInfo("Product info sample text","Code")
    val productFeatures = listOf(Cell.ProductSpecification("F1","V1"),Cell.ProductSpecification("F2","V2"))


    cells.add(productImage)
    cells.add(productPriceInfo)
    cells.add(productInfo)
    cells.add(Cell.ProductSpecificationLabel())
    cells.addAll(productFeatures)
    return cells.toList()
}

sealed interface Cell {
    data class ProductImage(val images: List<String?>) : Cell
    data class ProductPriceInfo(val price: String, val guaranteeClaimInfo:String, val includedGuaranteeInfo:String) : Cell
    data class ProductInfo(val productInfoText: String,val productCode: String) : Cell
    data class ProductSpecificationLabel(val string: String="Test") : Cell
    data class ProductSpecification(val feature:String, val value:String) : Cell
}

private fun dummyDetail(): Detail {
    return Detail(listOf("https://static.standard.co.uk/2023/06/27/10/android-logo.jpg?width=1200"),"100.00","","")
}

data class Detail(val images: List<String>,val price: String, val guaranteeClaimInfo:String, val includedGuaranteeInfo:String)