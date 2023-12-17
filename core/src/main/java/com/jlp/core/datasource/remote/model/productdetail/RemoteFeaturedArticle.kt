package com.jlp.core.datasource.remote.model.productdetail

data class RemoteFeaturedArticle(
    val image: String,
    val linkText: String,
    val linkUrl: String,
    val longDescription: String,
    val pdfUrl: String,
    val title: String
)