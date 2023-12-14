package com.jlp.core.datasource.remote.model.productlist

data class Crumb(
    val clickable: String,
    val displayName: String,
    val item: String,
    val type: String,
    val url: String
)