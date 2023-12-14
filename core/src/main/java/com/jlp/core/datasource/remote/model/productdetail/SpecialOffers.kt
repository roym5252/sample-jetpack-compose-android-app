package com.jlp.core.datasource.remote.model.productdetail

data class SpecialOffers(
    val bundleHeadline: String,
    val customPromotionalMessage: String,
    val customSpecialOffer: CustomSpecialOffer,
    val offer: String,
    val priceMatched: String
)