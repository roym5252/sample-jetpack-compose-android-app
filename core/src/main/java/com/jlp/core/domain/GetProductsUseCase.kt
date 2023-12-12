package com.jlp.core.domain

import com.jlp.core.datasource.ProductRepository
import com.jlp.core.model.Product
import com.jlp.core.util.TaskResult
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend operator fun invoke(): TaskResult<List<Product>?> {
        return productRepository.getProducts()
    }
}