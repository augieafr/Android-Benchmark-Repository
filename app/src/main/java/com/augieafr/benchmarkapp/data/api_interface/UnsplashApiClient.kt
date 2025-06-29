package com.augieafr.benchmarkapp.data.api_interface

import com.augieafr.benchmarkapp.data.model.response.UnsplashResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val SPLASH_API_KEY = "dhqoHgWxlRu6JNjOIpKX09ZROXvZtfEQXtOVoCffJZc"

interface UnsplashApiClient {
    @GET("photos")
    suspend fun getPhotos(
        @Query("client_id") apiKey: String = SPLASH_API_KEY,
        @Query("page") page: Int = 0,
        @Query("per_page") perPage: Int = 10
    ): Response<List<UnsplashResponse>>
}