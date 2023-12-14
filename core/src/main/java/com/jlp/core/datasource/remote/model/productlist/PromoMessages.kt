package com.jlp.core.datasource.remote.model.productlist

data class PromoMessages(
    val bundleHeadline: String,
    val customPromotionalMessage: String,
    val customSpecialOffer: CustomSpecialOffer,
    val offer: String,
    val priceMatched: String,
    val reducedToClear: Boolean
)