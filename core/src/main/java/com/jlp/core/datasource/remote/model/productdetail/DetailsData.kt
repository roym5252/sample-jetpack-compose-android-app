package com.jlp.core.datasource.remote.model.productdetail

data class DetailsData(
    val additionalServices: AdditionalServices,
    val ageRestriction: Int,
    val averageRating: Double,
    val brand: Brand,
    val bundleProducts: List<Any>,
    val code: String,
    val crumbs: List<Crumb>,
    val defaultCategory: DefaultCategory,
    val defaultSku: String,
    val deliveries: List<Delivery>,
    val deliveryFulfilledBy: String,
    val deliverySummary: List<DeliverySummary>,
    val details: Details,
    val displaySpecialOffer: String,
    val dynamicAttributes: DynamicAttributes,
    val emailMeWhenAvailable: Boolean,
    val excludeFromLiveChat: Boolean,
    val fixedRelatedProducts: List<Any>,
    val headingTypes: List<Any>,
    val isAsafShape: Boolean,
    val isFBL: Boolean,
    val isInTopNkuCategory: Boolean,
    val legs: List<Any>,
    val madeToMeasureDetails: MadeToMeasureDetails,
    val media: Media,
    val moreFromRange: List<Any>,
    val nonPromoMessage: String,
    val numberOfReviews: Int,
    val parentCategories: List<ParentCategory>,
    val preorderable: Boolean,
    val price: Price,
    val priceBands: List<Any>,
    val productId: String,
    val promotionalFeatures: List<PromotionalFeature>,
    val releaseDateTimestamp: Int,
    val seoInformation: SeoInformation,
    val seoURL: String,
    val setDetails: SetDetails,
    val setElements: List<Any>,
    val setInformation: String,
    val siblingSets: List<Any>,
    val skus: List<Sku>,
    val specialOfferBundles: List<Any>,
    val specialOffers: SpecialOffers,
    val storeOnly: Boolean,
    val swatchAvailable: String,
    val swatchCategoryType: String,
    val templateType: String,
    val title: String,
    val type: String,
    val webPimProductType: String
)