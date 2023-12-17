package com.jlp.core.datasource.remote.model.productdetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteDetailsData(

    @SerializedName("additionalServices")
    @Expose
    val additionalServices: RemoteAdditionalServices,

    @SerializedName("details")
    @Expose
    val details: RemoteDetails,
    val productId: String,

    @SerializedName("skus")
    @Expose
    val skus: List<RemoteSku>,
)