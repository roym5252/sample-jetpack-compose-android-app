package com.jlp.core.datasource

import com.jlp.core.datasource.remote.APIInterface
import com.jlp.core.model.Product
import com.jlp.core.model.ProductDetail
import com.jlp.core.util.ErrorType
import com.jlp.core.util.TaskResult
import retrofit2.awaitResponse
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiClient: APIInterface
) : ProductRepository {
    override suspend fun getProducts(): TaskResult<List<Product>?> {

        val response = apiClient.getProducts().awaitResponse()

        if (response.code() == 200) {

            val body = response.body()?.products

            body?.let { remoteProducts ->
                return TaskResult.Success(remoteProducts.map { Product.fromRemoteProduct(it) })
            }
        }

        return TaskResult.Error(ErrorType.UNEXPECTED)
    }

    override suspend fun getProductDetail(productId:Long): TaskResult<ProductDetail?> {

        val response = apiClient.getProductDetail(productId).awaitResponse()

        if (response.code() == 200) {

            val body = response.body()

            body?.let {

                if (body.remoteDetailsData.isNotEmpty()){
                    return TaskResult.Success(ProductDetail.fromRemoteProductDetail(body.remoteDetailsData[0]))
                }
            }
        }

        return TaskResult.Error(ErrorType.UNEXPECTED)
    }
}