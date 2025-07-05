package com.augieafr.benchmarkapp.ui.screen.scroll_test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.augieafr.benchmarkapp.data.model.response.UnsplashResponse
import com.augieafr.benchmarkapp.data.repository.UnsplashRepository
import kotlinx.coroutines.flow.Flow

class ScrollTestViewModel(private val repository: UnsplashRepository = UnsplashRepository()) :
    ViewModel() {

    val photos: Flow<PagingData<UnsplashResponse>> = repository.getPhotos()
        .cachedIn(viewModelScope)
}