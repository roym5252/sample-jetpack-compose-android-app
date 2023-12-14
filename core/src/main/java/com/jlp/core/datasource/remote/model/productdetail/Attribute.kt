package com.jlp.core.datasource.remote.model.productdetail

data class Attribute(
    val id: String,
    val multivalued: Boolean,
    val name: String,
    val toolTip: String,
    val uom: String,
    val value: String,
    val values: List<String>
)