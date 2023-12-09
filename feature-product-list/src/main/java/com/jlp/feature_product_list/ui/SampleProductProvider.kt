package com.jlp.feature_product_list.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class SampleProductProvider: PreviewParameterProvider<List<String>> {
    override val values: Sequence<List<String>> = sequenceOf(listOf("Product 1", "Product 2"))
}