package com.jlp.core.datasource.remote.model.productdetail

data class RemotePrice(
    val currency: String,
    val now: String,
    val then1: String,
    val then2: String,
    val uom: String,
    val was: String
)