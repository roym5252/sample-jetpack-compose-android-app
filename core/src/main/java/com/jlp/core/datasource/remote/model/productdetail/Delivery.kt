package com.jlp.core.datasource.remote.model.productdetail

data class Delivery(
    val deliveryType: String,
    val options: List<Option>
)