package com.jlp.core.datasource.remote.model.productlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteProduct(
    val ageRestriction: Int,
    val alternativeImageUrls: List<String>,
    val availabilityMessage: String,
    val averageRating: Double,
    val brand: String,
    val categoryQuickViewEnabled: Boolean,
    val code: String,
    val colorSwatchSelected: Int,

    @SerializedName("colorSwatches")
    @Expose
    val colorSwatches: List<RemoteColorSwatche>,
    val colorWheelMessage: String,
    val compare: Boolean,
    val defaultSkuId: String,
    val directorate: String,
    val displaySpecialOffer: String,

    @SerializedName("dynamicAttributes")
    @Expose
    val dynamicAttributes: RemoteDynamicAttributes,
    val emailMeWhenAvailable: Boolean,
    val fabric: String,
    val fabricByLength: Boolean,
    val features: List<Any>,
    val futureRelease: Boolean,
    val htmlTitle: String,
    val image: String,
    val isBundle: Boolean,
    val isInStoreOnly: Boolean,
    val isMadeToMeasure: Boolean,
    val isProductSet: Boolean,

    @SerializedName("messaging")
    @Expose
    val messaging: List<RemoteMessaging>,
    val multiSku: Boolean,
    val nonPromoMessage: String,
    val outOfStock: Boolean,
    val permanentOos: Boolean,

    @SerializedName("price")
    @Expose
    val price: RemotePrice,
    val productId: String,

    @SerializedName("promoMessages")
    @Expose
    val promoMessages: RemotePromoMessages,
    val promotionalFeatures: List<Any>,

    @SerializedName("quickAddToBasket")
    @Expose
    val quickAddToBasket: RemoteQuickAddToBasket,
    val reviews: Int,
    val swatchAvailable: Boolean,
    val swatchCategoryType: String,
    val title: String,
    val type: String
)