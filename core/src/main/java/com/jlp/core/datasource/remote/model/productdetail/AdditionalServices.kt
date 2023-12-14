package com.jlp.core.datasource.remote.model.productdetail

data class AdditionalServices(
    val includedServices: List<String>,
    val optionalServices: List<OptionalService>
)