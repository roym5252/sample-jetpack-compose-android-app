package com.jlp.core.datasource.remote.model.productdetail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteDetails(

    @SerializedName("buyingGuides")
    @Expose
    val buyingGuides: List<RemoteBuyingGuide>,
    val careGuide: List<Any>,
    val editorsNotes: String,

    @SerializedName("featuredArticles")
    @Expose
    val featuredArticles: List<RemoteFeaturedArticle>,

    @SerializedName("features")
    @Expose
    val features: List<RemoteFeature>,
    val productInformation: String,
    val returns: String,
    val returnsHeadline: String,
    val sizeGuides: List<Any>,
    val termsAndConditions: String,
    val weLikeItBecause: String
)