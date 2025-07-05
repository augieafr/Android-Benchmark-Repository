package com.augieafr.benchmarkapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.augieafr.benchmarkapp.data.api_interface.UnsplashApiClient
import com.augieafr.benchmarkapp.data.model.response.UnsplashResponse
import com.augieafr.benchmarkapp.data.network.ApiClient
import com.augieafr.benchmarkapp.data.paging.UnsplashPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class UnsplashRepository {
    private val unsplashApiClient = ApiClient.createRetrofitClient(ApiClient.UNSPLASH_API_URL)
        .create(UnsplashApiClient::class.java)

    fun getPhotos(): Flow<PagingData<UnsplashResponse>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            initialLoadSize = 20
        ),
        pagingSourceFactory = {
            UnsplashPagingSource(unsplashApiClient)
        }
    ).flow.flowOn(Dispatchers.IO)
}