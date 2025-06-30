package com.augieafr.kmpbenchmarkapp.ui.screen.scroll_test

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.augieafr.kmpbenchmarkapp.data.model.response.UnsplashResponse
import com.augieafr.kmpbenchmarkapp.data.repository.UnsplashRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScrollTestViewModel(private val repository: UnsplashRepository) :
    ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    val photos: Flow<PagingData<UnsplashResponse>> = repository.getPhotos()
}