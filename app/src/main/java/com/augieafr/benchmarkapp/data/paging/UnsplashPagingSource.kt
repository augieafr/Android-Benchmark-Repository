package com.augieafr.benchmarkapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.augieafr.benchmarkapp.data.api_interface.UnsplashApiClient
import com.augieafr.benchmarkapp.data.model.response.UnsplashResponse
import retrofit2.HttpException

class UnsplashPagingSource(private val unsplashApiClient: UnsplashApiClient) :
    PagingSource<Int, UnsplashResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashResponse> {
        return try {
            val page = params.key ?: 0
            val response = unsplashApiClient.getPhotos(
                page = page,
                perPage = params.loadSize
            )

            val prevKey = if (page == 0) null else page - 1
            val nextKey = if (response.body().isNullOrEmpty()) null else page + 1

            if (response.isSuccessful) {
                LoadResult.Page(
                    data = response.body() ?: emptyList(),
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(Exception(response.message()))
            }
        } catch (ex: HttpException) {
            LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}