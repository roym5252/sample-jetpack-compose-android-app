package com.jlp.core.datasource.remote.model

data class PageInformation(
    val description: String,
    val heading: String,
    val noFollow: Boolean,
    val noIndex: Boolean,
    val title: String
)