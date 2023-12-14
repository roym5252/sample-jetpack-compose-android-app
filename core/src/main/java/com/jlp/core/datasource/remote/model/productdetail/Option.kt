package com.jlp.core.datasource.remote.model.productdetail

data class Option(
    val currency: String,
    val cutOffTime: Int,
    val date: String,
    val dateMessage: String,
    val id: String,
    val isApprovedSupplier: Boolean,
    val leadTime: Int,
    val newPriority: Int,
    val newShortDescription: String,
    val newStandardDescription: String,
    val price: String,
    val shortDescription: String,
    val standardDescription: String,
    val trialMessage: String
)