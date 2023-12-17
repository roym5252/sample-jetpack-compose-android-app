package com.jlp.core.datasource.remote.model.productlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteProductResponse(

    @SerializedName("products")
    @Expose
    val products: List<RemoteProduct>?,
)