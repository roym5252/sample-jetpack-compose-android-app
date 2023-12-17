package com.jlp.feature_product_detail.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jlp.core.domain.GetProductDetailUseCase
import com.jlp.core.util.CommonUtil
import com.jlp.core.util.TaskResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    val commonUtil: CommonUtil,
    application: Application,
    val getProductDetailUseCase: GetProductDetailUseCase,
) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(ProductDetailScreenUiState())
    val uiState: StateFlow<ProductDetailScreenUiState> = _uiState

    private lateinit var getProductsJob: Job

    /**
     * Function for fetching product detail.
     */
    private fun getProductDetail(productId:Long) {

        _uiState.value = ProductDetailScreenUiState(true)

        getProductsJob = viewModelScope.launch {

            if (commonUtil.isInternetConnected(getApplication())) {

                when (val result = getProductDetailUseCase(productId)) {

                    is TaskResult.Success -> {

                        val productDetail = result.data

                        if (productDetail != null) {

                            _uiState.value = ProductDetailScreenUiState(
                                loading = false,
                                productDetail = productDetail
                            )

                        } else {

                            _uiState.value = ProductDetailScreenUiState(
                                loading = false,
                                infoMessage = com.jlp.core.R.string.unexpected_error_retry
                            )
                        }

                    }

                    is TaskResult.Error -> {

                        _uiState.value = ProductDetailScreenUiState(
                            loading = false,
                            infoMessage = com.jlp.core.R.string.unexpected_error_retry
                        )
                    }
                }

            } else {

                _uiState.emit(
                    ProductDetailScreenUiState(
                        false, infoMessage = com.jlp.core.R.string.no_internet_message
                    )
                )
            }

        }
    }

    /**
     * Method for cancelling the product detail fetching job.
     */
    private fun cancelGetProductDetailJob() {
        getProductsJob.cancel()
    }

    fun onStart(productId: Long) {
        getProductDetail(productId)
    }

    fun onStop() {
        cancelGetProductDetailJob()
    }
}