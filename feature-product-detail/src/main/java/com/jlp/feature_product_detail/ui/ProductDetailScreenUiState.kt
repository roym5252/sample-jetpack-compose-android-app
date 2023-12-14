package com.jlp.feature_product_detail.ui

import com.jlp.core.model.Product

data class ProductDetailScreenUiState(

    val loading: Boolean = true,
    val product: Product? = null,
    val showReloadIcon: Boolean = false,
    val infoMessage: Int? = null
)