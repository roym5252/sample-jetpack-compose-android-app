package com.jlp.core.datasource.remote.model.productdetail

data class DeliverySummary(
    val currency: String,
    val deliveryType: String,
    val newOptionId: String,
    val newPriority: Int,
    val newSummary: String,
    val newTitle: String,
    val price: String,
    val summary: String,
    val title: String,
    val trialMessage: String
)