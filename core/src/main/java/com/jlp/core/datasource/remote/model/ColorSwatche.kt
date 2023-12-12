package com.jlp.core.datasource.remote.model

data class ColorSwatche(
    val basicColor: String,
    val color: String,
    val colorSwatchUrl: String,
    val imageUrl: String,
    val isAvailable: Boolean,
    val skuId: String
)