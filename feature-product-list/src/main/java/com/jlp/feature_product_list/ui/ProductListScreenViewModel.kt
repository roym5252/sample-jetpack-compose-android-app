package com.jlp.feature_product_list.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jlp.core.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListScreenViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(ProductListScreenUiState())
    val uiState: StateFlow<ProductListScreenUiState> = _uiState

    private lateinit var getProductsJob: Job

    /**
     * Function for fetching random images every 10 seconds.
     */
    private fun getProducts() {

        getProductsJob = viewModelScope.launch {
            _uiState.emit(
                ProductListScreenUiState(
                    false, products = listOf(
                        Product("Product 1", "ssd", "99.99"),
                        Product("Product 2", "ssd", "109.99")
                    )
                )
            )
        }
    }

    /**
     * Method for cancelling the image fetching job.
     */
    private fun cancelRandomImageFetchJob() {
        getProductsJob.cancel()
    }

    fun onStart() {
        getProducts()
    }

    fun onStop() {
        cancelRandomImageFetchJob()
    }
}