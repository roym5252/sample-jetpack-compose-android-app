package com.jlp.feature_product_detail.ui

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import com.jlp.core.model.ProductDetail
import com.jlp.core.ui.theme.CustomColor
import com.jlp.featire_product_detail.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductDetailList(productDetail: ProductDetail, isTablet: Boolean = false) {

    val pagerState = rememberPagerState(pageCount = {
        productDetail.imageUrls.size
    })

    val isExpanded = remember { mutableStateOf(false) }
    val isClickable = remember { mutableStateOf(false) }


    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

        Box(
            modifier = Modifier
                .weight(1f)
                .background(CustomColor.Light_Grey)
                .fillMaxSize()
        ) {
            LazyColumn(content = {

                items(toCell(productDetail, isTablet)) { it ->

                    when (it) {

                        is Cell.ProductImage -> {
                            ProductImagesCellComposable(isTablet, pagerState, productDetail)
                        }

                        is Cell.ProductPriceInfo -> {
                            ProductPriceCellComposable(isTablet, it)
                        }

                        is Cell.ProductInfo -> {
                            ProductInfoCellComposable(
                                isTablet,
                                it,
                                productDetail,
                                isExpanded,
                                isClickable
                            )
                        }

                        is Cell.ProductSpecificationLabel -> {

                            ProductSpecificationLabelCellComposable(isTablet)
                        }

                        is Cell.ProductSpecification -> {
                            ProductSpecificationCellComposable(isTablet, it)
                        }
                    }
                }
            }, modifier = Modifier.testTag("productDetailList"))
        }

        if (isTablet) {
            Box(
                modifier = Modifier
                    .background(CustomColor.Light_Grey)
                    .fillMaxSize()
                    .weight(.60f)
                    .padding(top = 4.dp, end = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxSize()
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {

                        val productPriceInfo = Cell.ProductPriceInfo(
                            if (productDetail.price != null) productDetail.price!! else "--",
                            stringResource(id = R.string.claim_guarantee_text),
                            productDetail.additionalIncludedServices
                        )

                        ProductPriceInfoCell(productPriceInfo)
                    }
                }
            }


        }

    }
}

@Composable
private fun ProductSpecificationCellComposable(
    isTablet: Boolean,
    it: Cell.ProductSpecification
) {
    Box(modifier = Modifier.background(CustomColor.Light_Grey)) {

        Box(
            modifier = Modifier.padding(
                start = 8.dp,
                end = if (isTablet) 4.dp else 16.dp
            )
        ) {

            Column(
                Modifier
                    .padding(start = 8.dp)
                    .background(Color.White)
                    .semantics(mergeDescendants = true) {}) {

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 8.dp,
                            end = 8.dp,
                            top = 16.dp,
                            bottom = 16.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = it.feature,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp, end = 4.dp)
                            .testTag("FeatureLabel-${it.feature}"),
                        fontWeight = FontWeight(600),
                        fontFamily = FontFamily(Font(com.jlp.core.R.font.montserrat_medium))
                    )
                    Text(
                        text = it.value,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .weight(1f)
                            .testTag("FeatureValue-${it.value}")
                            .padding(start = 8.dp),
                        fontWeight = FontWeight(600),
                        fontFamily = FontFamily(Font(com.jlp.core.R.font.montserrat_light))
                    )
                }

                Box(
                    modifier = Modifier
                        .height(2.dp)
                        .padding(start = 8.dp, end = 8.dp)
                        .background(CustomColor.Light_Grey)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun ProductSpecificationLabelCellComposable(isTablet: Boolean) {
    Box(modifier = Modifier.background(CustomColor.Light_Grey)) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = if (isTablet) 4.dp else 16.dp)
                .background(Color.White)
        ) {

            Column {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 8.dp,
                            end = 8.dp,
                            top = 4.dp,
                            bottom = 8.dp
                        )
                        .background(Color.White)
                ) {


                    Text(
                        text = stringResource(id = R.string.product_specification),
                        modifier = Modifier
                            .testTag("productSpecificationLabel")
                            .padding(top = 8.dp, bottom = 8.dp)
                            .semantics { heading() },
                        fontSize = 25.sp,
                        fontWeight = FontWeight(800),
                        fontFamily = FontFamily(Font(com.jlp.core.R.font.montserrat_extra_light))
                    )
                }

                Box(
                    modifier = Modifier
                        .height(2.dp)
                        .padding(start = 8.dp)
                        .background(CustomColor.Light_Grey)
                        .fillMaxWidth()
                )
            }

        }
    }
}

@Composable
private fun ProductInfoCellComposable(
    isTablet: Boolean,
    it: Cell.ProductInfo,
    productDetail: ProductDetail,
    isExpanded: MutableState<Boolean>,
    isClickable: MutableState<Boolean>
) {
    Box(modifier = Modifier.background(CustomColor.Light_Grey)) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = if (isTablet) 4.dp else 16.dp)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.White)
            ) {
                Text(
                    text = stringResource(id = R.string.product_information),
                    modifier = Modifier
                        .testTag("productInfoLabel")
                        .padding(bottom = 8.dp)
                        .semantics { heading() },
                    fontSize = 25.sp,
                    fontWeight = FontWeight(800),
                    fontFamily = FontFamily(Font(com.jlp.core.R.font.montserrat_extra_light))
                )

                Text(
                    text = "${stringResource(id = R.string.product_code)} ${it.productCode}",
                    modifier = Modifier
                        .testTag("productCode")
                        .padding(bottom = 4.dp),
                    fontFamily = FontFamily(Font(com.jlp.core.R.font.montserrat_medium)),
                    fontSize = 20.sp
                )

                val spannableString =
                    SpannableStringBuilder(
                        productDetail.productInfo!!.replace(
                            "<p><strong>",
                            "<br/><p><strong>"
                        )
                    ).toString()
                val spanned = HtmlCompat.fromHtml(
                    spannableString,
                    HtmlCompat.FROM_HTML_MODE_COMPACT
                )

                ExpandableText(
                    modifier = Modifier.testTag("productInfoText"),
                    text = spanned.toAnnotatedString(),
                    isExpanded = isExpanded,
                    isClickable = isClickable
                )

            }
        }
    }
}

@Composable
private fun ProductPriceCellComposable(isTablet: Boolean, it: Cell.ProductPriceInfo) {
    Box(modifier = Modifier.background(CustomColor.Light_Grey)) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = if (isTablet) 4.dp else 16.dp)
                .background(Color.White)
        ) {
            ProductPriceInfoCell(it)
        }

    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun ProductImagesCellComposable(
    isTablet: Boolean,
    pagerState: PagerState,
    productDetail: ProductDetail
) {
    Box(modifier = Modifier.background(CustomColor.Light_Grey)) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    bottom = if (isTablet) 0.dp else 8.dp,
                    end = if (isTablet) 4.dp else 16.dp,
                    top = 4.dp
                )
                .background(Color.White)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("productDetailPager")
            ) { page ->
                AsyncImage(
                    model = productDetail.imageUrls[page],
                    contentDescription = null,
                    modifier = Modifier
                        .clickable(onClickLabel = "ProductDetailImage-$page") {
                        }
                        .testTag("productDetailImage-$page")
                        .height(400.dp)
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    contentScale = ContentScale.Inside,
                    placeholder = painterResource(id = com.jlp.core.R.drawable.ic_placeholder)
                )
            }
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, top = 8.dp)
                    .testTag("productDetailPageIndicator"),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.Black else CustomColor.Light_Grey
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(10.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductPriceInfoCell(it: Cell.ProductPriceInfo) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = it.price,
            modifier = Modifier.testTag("price"),
            fontSize = 30.sp,
            fontWeight = FontWeight(800),
            fontFamily = FontFamily(Font(com.jlp.core.R.font.montserrat_medium))
        )
        Text(
            text = it.guaranteeClaimInfo,
            fontWeight = FontWeight(800),
            modifier = Modifier
                .testTag("guaranteeClaimInfo")
                .padding(top = 4.dp),
            fontFamily = FontFamily(Font(com.jlp.core.R.font.montserrat_medium)),
            lineHeight = 25.sp,
            color = CustomColor.BrickRed,
            fontSize = 18.sp
        )
        Text(
            text = it.includedGuaranteeInfo,
            modifier = Modifier
                .padding(top = 4.dp)
                .testTag("includedGuaranteeInfo"),
            fontFamily = FontFamily(Font(com.jlp.core.R.font.montserrat_medium)),
            lineHeight = 25.sp,
            color = CustomColor.Teal,
            fontSize = 18.sp
        )
    }
}

fun toCell(productDetail: ProductDetail, isTablet: Boolean): List<Cell> {

    val cells = mutableListOf<Cell>()

    val productImage = Cell.ProductImage(productDetail.imageUrls)
    val productPriceInfo = Cell.ProductPriceInfo(
        if (productDetail.price != null) productDetail.price!! else "--",
        "Claim an extra 3 years guarantee via redemption",
        productDetail.additionalIncludedServices
    )
    val productInfo = Cell.ProductInfo(
        if (productDetail.productInfo != null) productDetail.productInfo!! else "No product information available.",
        if (productDetail.code != null) productDetail.code!! else "No Product Code Available"
    )
    val productFeatures = productDetail.features.map {
        Cell.ProductSpecification(it.first, it.second)
    }

    cells.add(productImage)

    if (!isTablet) {
        cells.add(productPriceInfo)
    }

    cells.add(productInfo)
    cells.add(Cell.ProductSpecificationLabel())
    cells.addAll(productFeatures)
    return cells.toList()
}

sealed interface Cell {
    data class ProductImage(val images: List<String?>) : Cell
    data class ProductPriceInfo(
        val price: String,
        val guaranteeClaimInfo: String,
        val includedGuaranteeInfo: String
    ) : Cell

    data class ProductInfo(val productInfoText: String, val productCode: String) : Cell
    data class ProductSpecificationLabel(val text: String = "") : Cell
    data class ProductSpecification(val feature: String, val value: String) : Cell
}

fun Spanned.toAnnotatedString(): AnnotatedString = buildAnnotatedString {
    val spanned = this@toAnnotatedString
    append(spanned.toString())
    getSpans(0, spanned.length, Any::class.java).forEach { span ->
        val start = getSpanStart(span)
        val end = getSpanEnd(span)
        when (span) {
            is StyleSpan -> when (span.style) {
                Typeface.BOLD -> addStyle(SpanStyle(fontWeight = FontWeight.Bold), start, end)
                Typeface.ITALIC -> addStyle(SpanStyle(fontStyle = FontStyle.Italic), start, end)
                Typeface.BOLD_ITALIC -> addStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    ), start, end
                )
            }

            is UnderlineSpan -> addStyle(
                SpanStyle(textDecoration = TextDecoration.Underline),
                start,
                end
            )

            is ForegroundColorSpan -> addStyle(
                SpanStyle(color = Color(span.foregroundColor)),
                start,
                end
            )
        }
    }
}

