package com.jlp.core.datasource.remote.model.productdetail

data class RemoteOptionalService(
    val associatedProductId: String,
    val currency: String,
    val description: String,
    val id: String,
    val orderOnSite: Int,
    val price: String,
    val title: String,
    val type: String,
    val url: String
)