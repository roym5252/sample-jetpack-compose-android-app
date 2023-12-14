package com.jlp.core.datasource.remote.model.productdetail

data class Details(
    val buyingGuides: List<BuyingGuide>,
    val careGuide: List<Any>,
    val editorsNotes: String,
    val featuredArticles: List<FeaturedArticle>,
    val features: List<Feature>,
    val productInformation: String,
    val returns: String,
    val returnsHeadline: String,
    val sizeGuides: List<Any>,
    val termsAndConditions: String,
    val weLikeItBecause: String
)