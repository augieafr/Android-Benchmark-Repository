package com.augieafr.kmpbenchmarkapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.augieafr.kmpbenchmarkapp.data.api_interface.UnsplashApiClient
import com.augieafr.kmpbenchmarkapp.data.model.response.UnsplashResponse
import com.augieafr.kmpbenchmarkapp.data.paging.UnsplashPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class UnsplashRepository(
    private val unsplashApiClient: UnsplashApiClient
) {
    fun getPhotos(): Flow<PagingData<UnsplashResponse>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            initialLoadSize = 10
        ),
        pagingSourceFactory = {
            UnsplashPagingSource(unsplashApiClient)
        }
    ).flow.flowOn(Dispatchers.IO)
}
