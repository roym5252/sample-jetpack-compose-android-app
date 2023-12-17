package com.jlp.core.datasource.remote.model.productdetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteSku(
    val code: String,

    @SerializedName("media")
    @Expose
    val media: RemoteMedia,

    @SerializedName("price")
    @Expose
    val price: RemotePrice,
)