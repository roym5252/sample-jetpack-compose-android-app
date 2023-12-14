package com.jlp.core.datasource.remote.model.productdetail

data class Availability(
    val availabilityStatus: String,
    val message: String,
    val stockLevel: Int
)