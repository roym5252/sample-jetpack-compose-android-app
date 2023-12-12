package com.jlp.core.model

data class Product(val title: String, val image: String?, val price: String) {
    companion object {

        fun fromRemoteProduct(remoteProduct: com.jlp.core.datasource.remote.model.Product): Product {

            return Product(
                remoteProduct.title,
                "https:${remoteProduct.image}",
                (if (remoteProduct.price.currency == "GBP") "£" else remoteProduct.price.currency) + remoteProduct.price.now
            )
        }
    }
}