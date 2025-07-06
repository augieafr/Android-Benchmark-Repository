package com.augieafr.kmpbenchmarkapp.ui.screen.scroll_test

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.augieafr.kmpbenchmarkapp.data.model.response.UnsplashResponse
import com.augieafr.kmpbenchmarkapp.data.repository.UnsplashRepository
import kotlinx.coroutines.flow.Flow

class ScrollTestViewModel(private val repository: UnsplashRepository) :
    ViewModel() {

    val photos: Flow<PagingData<UnsplashResponse>> = repository.getPhotos()
}