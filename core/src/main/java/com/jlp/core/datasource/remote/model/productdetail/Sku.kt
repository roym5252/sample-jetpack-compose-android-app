package com.jlp.core.datasource.remote.model.productdetail

data class Sku(
   /* val availability: Availability,
    val brandName: String,*/
    val code: String,
    /*val color: String,
    val d2cDeliveryLeadTime: String,*/
    /*val dynamicAttributes: DynamicAttributesX,
    val id: String,
    val leadTime: String,
    val mainframeProductId: String,*/
    val media: Media,
    val price: Price,
   /* val priceBand: String,
    val shortSkuTitle: String,
    val size: String,
    val sizeHeadline: String,
    val skuTitle: String,
    val swatchUrl: String,
    val ticketType: String,
    val unitPriceInfo: UnitPriceInfo*/
)