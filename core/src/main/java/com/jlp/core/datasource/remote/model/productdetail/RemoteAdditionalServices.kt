package com.jlp.core.datasource.remote.model.productdetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteAdditionalServices(
    @SerializedName("includedServices")
    @Expose
    val remoteIncludedServices: List<String>,

    @SerializedName("optionalServices")
    @Expose
    val remoteOptionalServices: List<RemoteOptionalService>
)