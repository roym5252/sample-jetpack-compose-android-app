package com.jlp.core.datasource.remote.model

data class Price(
    val currency: String,
    val now: String,
    val then1: String,
    val then2: String,
    val uom: String,
    val was: String
)