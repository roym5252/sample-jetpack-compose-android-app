package com.jlp.core.datasource.remote.model.productdetail

data class Videos(
    val imgAltText: String,
    val prod_vid_thmb: String,
    val videoHeight: String,
    val videoHost: String,
    val videoImagePath: String,
    val videoWidth: String,
    val videosList: List<VideosX>
)