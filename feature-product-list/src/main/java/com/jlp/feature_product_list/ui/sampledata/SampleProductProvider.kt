package com.jlp.feature_product_list.ui.sampledata

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.jlp.core.model.Product

class SampleProductProvider: PreviewParameterProvider<List<Product>> {
    override val values: Sequence<List<Product>> = sequenceOf(listOf(Product("Product 1","","")))
}