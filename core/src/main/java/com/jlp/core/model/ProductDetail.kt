package com.jlp.core.model

import com.jlp.core.datasource.remote.model.productdetail.DetailsData

data class ProductDetail(
    val productId: Long,
    val price: String?,
    val imageUrls: List<String>,
    val code: String?,
    val productInfo: String?,
    val features: List<Pair<String, String>>,
    val additionalIncludedServices:String,
    val optionalServices:String,
) {

    companion object {

        fun fromRemoteProductDetail(detailsData: DetailsData): ProductDetail {

            var features = listOf<Pair<String, String>>()
            var includedServices = ""
            val optionalServices = ""

            if (detailsData.details.features.isNotEmpty()) {
                features = detailsData.details.features[0].attributes.map {
                    Pair(it.name, it.value)
                }
            }

            if (detailsData.additionalServices.includedServices.isNotEmpty()) {
                detailsData.additionalServices.includedServices.forEach {
                    includedServices= "$includedServices$it, "
                }
            }

            /*if (detailsData.additionalServices.optionalServices.isNotEmpty()) {
                detailsData.additionalServices.optionalServices.forEach {
                    optionalServices= "$optionalServices$it, "
                }
            }*/

            if (detailsData.skus.isNotEmpty()) {

                return ProductDetail(
                    detailsData.productId.toLong(),
                    (if (detailsData.skus[0].price.currency.contentEquals("GBP")) "£" else detailsData.skus[0].price.currency) + detailsData.skus[0].price.now,
                    detailsData.skus[0].media.images.urls.map { "https:$it" },
                    detailsData.skus[0].code,
                    detailsData.details.productInformation, features,
                    includedServices.replace(", ",""),
                    optionalServices.replace(", ",""),
                )

            } else {

                return ProductDetail(
                    detailsData.productId.toLong(),
                    "",
                    listOf(),
                    "",
                    detailsData.details.productInformation, features,
                    includedServices,
                    optionalServices
                )
            }

        }
    }
}