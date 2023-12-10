package com.jlp.feature_product_list.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProductListScreenViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(ProductListScreenUiState())
    val uiState: StateFlow<ProductListScreenUiState> = _uiState



    fun onStart() {

    }

    fun onStop() {

    }
}