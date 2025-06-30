package com.augieafr.kmpbenchmarkapp.data.paging

import app.cash.paging.PagingSource
import app.cash.paging.PagingState
import com.augieafr.kmpbenchmarkapp.data.api_interface.UnsplashApiClient
import com.augieafr.kmpbenchmarkapp.data.model.response.UnsplashResponse

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
            val nextKey = if (response.isEmpty()) null else page + 1

            LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (ex: Exception) {
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