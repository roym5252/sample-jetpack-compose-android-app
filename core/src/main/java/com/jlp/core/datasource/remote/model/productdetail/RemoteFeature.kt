package com.jlp.core.datasource.remote.model.productdetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteFeature(

    @SerializedName("attributes")
    @Expose
    val attributes: List<RemoteAttribute>,
    val groupName: String
)