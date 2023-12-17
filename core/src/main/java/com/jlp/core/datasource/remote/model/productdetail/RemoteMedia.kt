package com.jlp.core.datasource.remote.model.productdetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteMedia(

    @SerializedName("images")
    @Expose
    val images: RemoteImagesX
)