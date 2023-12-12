package com.jlp.core.datasource

import com.jlp.core.model.Product
import com.jlp.core.util.TaskResult

interface ProductRepository {
    suspend fun getProducts(): TaskResult<List<Product>?>
}