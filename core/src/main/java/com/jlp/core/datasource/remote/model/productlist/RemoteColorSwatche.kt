package com.jlp.core.datasource.remote.model.productlist

data class RemoteColorSwatche(
    val basicColor: String,
    val color: String,
    val colorSwatchUrl: String,
    val imageUrl: String,
    val isAvailable: Boolean,
    val skuId: String
)