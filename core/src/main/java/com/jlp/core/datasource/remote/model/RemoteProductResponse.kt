package com.jlp.core.datasource.remote.model

data class RemoteProductResponse(
    /*val banners: Banners,
    val baseFacetId: String,
    val categoryTitle: String,
    val crumbs: List<Crumb>,
    val dynamicBannerId: String,
    val endecaCanonical: String,
    val facets: List<Facet>,
    val pageInformation: PageInformation,
    val pagesAvailable: Int,*/
    val products: List<Product>?,
    /*val results: Int,
    val seoBannerId: String,
    val seoInformation: SeoInformation,
    val showInStockOnly: Boolean,
    val triggeredRules: TriggeredRules*/
)