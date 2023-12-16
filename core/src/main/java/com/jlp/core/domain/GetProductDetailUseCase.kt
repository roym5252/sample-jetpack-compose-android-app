package com.jlp.core.domain

import com.jlp.core.datasource.ProductRepository
import com.jlp.core.model.ProductDetail
import com.jlp.core.util.TaskResult
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend operator fun invoke(productId:Long): TaskResult<ProductDetail?> {
        return productRepository.getProductDetail(productId)
    }
}