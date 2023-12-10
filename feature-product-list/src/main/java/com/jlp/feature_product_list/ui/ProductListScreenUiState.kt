package com.jlp.feature_product_list.ui

import com.jlp.core.model.Product

data class ProductListScreenUiState(
    val loading: Boolean = true,
    val products:List<Product>? = null,
    val showReloadIcon: Boolean = false,
    val errorMessage: String? = null
)
