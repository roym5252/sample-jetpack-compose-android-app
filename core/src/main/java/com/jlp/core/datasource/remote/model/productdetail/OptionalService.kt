package com.jlp.core.datasource.remote.model.productdetail

data class OptionalService(
    val associatedProductId: String,
    val currency: String,
    //val customProperties: CustomProperties,
    val description: String,
    val id: String,
    val orderOnSite: Int,
    val price: String,
    val title: String,
    val type: String,
    val url: String
)