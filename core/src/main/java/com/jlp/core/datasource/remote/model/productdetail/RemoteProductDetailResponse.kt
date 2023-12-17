package com.jlp.core.datasource.remote.model.productdetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteProductDetailResponse(
    @SerializedName("detailsData")
    @Expose
    val remoteDetailsData: List<RemoteDetailsData>
)