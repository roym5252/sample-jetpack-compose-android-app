package com.jlp.feature_product_detail.ui

import com.jlp.core.model.ProductDetail

data class ProductDetailScreenUiState(

    val loading: Boolean = true,
    val productDetail: ProductDetail? = null,
    val showReloadIcon: Boolean = false,
    val infoMessage: Int? = null
)