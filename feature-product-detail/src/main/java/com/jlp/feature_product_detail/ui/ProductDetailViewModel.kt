package com.jlp.feature_product_detail.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jlp.core.model.Product
import com.jlp.core.util.CommonUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(val commonUtil: CommonUtil,
    application: Application,
) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(ProductDetailScreenUiState())
    val uiState: StateFlow<ProductDetailScreenUiState> = _uiState

    private lateinit var getProductsJob: Job

    /**
     * Function for fetching products.
     */
    private fun getProductDetail() {

        _uiState.value = ProductDetailScreenUiState(true)

        getProductsJob = viewModelScope.launch {

            if (commonUtil.isInternetConnected(getApplication())) {

                _uiState.value = ProductDetailScreenUiState(false, product = Product(1L,"Test","","100.00"))
                /*when(val result = getProductsUseCase()){

                    is TaskResult.Success->{

                        val products = result.data

                        if (products!=null){

                            _uiState.value = ProductDetailScreenUiState(
                                loading = false,
                                products = products
                            )

                        }else{

                            _uiState.value = ProductDetailScreenUiState(
                                loading = false,
                                infoMessage = R.string.unexpected_error_retry
                            )
                        }

                    }

                    is TaskResult.Error ->{

                        _uiState.value = ProductDetailScreenUiState(
                            loading = false,
                            infoMessage = R.string.unexpected_error_retry
                        )
                    }
                }*/

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
     * Method for cancelling the product fetching job.
     */
    private fun cancelGetProductDetailJob() {
        getProductsJob.cancel()
    }

    fun onStart() {
        getProductDetail()
    }

    fun onStop() {
        cancelGetProductDetailJob()
    }
}