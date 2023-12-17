package com.jlp.core.datasource.remote.model.productlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemotePromoMessages(
    val bundleHeadline: String,
    val customPromotionalMessage: String,

    @SerializedName("customSpecialOffer")
    @Expose
    val customSpecialOffer: RemoteCustomSpecialOffer,
    val offer: String,
    val priceMatched: String,
    val reducedToClear: Boolean
)