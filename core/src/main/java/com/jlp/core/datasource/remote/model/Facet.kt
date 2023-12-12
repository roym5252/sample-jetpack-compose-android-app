package com.jlp.core.datasource.remote.model

data class Facet(
    val details: List<Detail>,
    val dimensionName: String,
    val filterTypes: List<String>,
    val name: String,
    val tooltip: String,
    val type: String
)