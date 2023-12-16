package com.jlp.core.util

object Constant {
    const val BASE_URL ="https://api.johnlewis.com"
    const val API_SEARCH ="/search/api/rest/v2/catalog/products/search/keyword"
    const val API_DETAIL ="/api/v1/products"

}

enum class ErrorType {
    NO_CONNECTION,UNEXPECTED
}