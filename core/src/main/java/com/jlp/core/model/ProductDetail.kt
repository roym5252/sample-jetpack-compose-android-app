package com.jlp.core.model

import com.jlp.core.datasource.remote.model.productdetail.RemoteDetailsData

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

        fun fromRemoteProductDetail(remoteDetailsData: RemoteDetailsData): ProductDetail {

            var features = listOf<Pair<String, String>>()
            var includedServices = ""
            val optionalServices = ""

            if (remoteDetailsData.details.features.isNotEmpty()) {
                features = remoteDetailsData.details.features[0].attributes.map {
                    Pair(it.name, it.value)
                }
            }

            if (remoteDetailsData.additionalServices.remoteIncludedServices.isNotEmpty()) {
                remoteDetailsData.additionalServices.remoteIncludedServices.forEach {
                    includedServices= "$includedServices$it, "
                }
            }

            if (remoteDetailsData.skus.isNotEmpty()) {

                return ProductDetail(
                    remoteDetailsData.productId.toLong(),
                    (if (remoteDetailsData.skus[0].price.currency.contentEquals("GBP")) "£" else remoteDetailsData.skus[0].price.currency) + remoteDetailsData.skus[0].price.now,
                    remoteDetailsData.skus[0].media.images.urls.map { "https:$it" },
                    remoteDetailsData.skus[0].code,
                    remoteDetailsData.details.productInformation, features,
                    includedServices.replace(", ",""),
                    optionalServices.replace(", ",""),
                )

            } else {

                return ProductDetail(
                    remoteDetailsData.productId.toLong(),
                    "",
                    listOf(),
                    "",
                    remoteDetailsData.details.productInformation, features,
                    includedServices,
                    optionalServices
                )
            }

        }
    }
}