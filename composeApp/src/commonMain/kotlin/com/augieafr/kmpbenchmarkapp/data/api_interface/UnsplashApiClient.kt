package com.augieafr.kmpbenchmarkapp.data.api_interface

import com.augieafr.kmpbenchmarkapp.data.model.response.UnsplashResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

private const val SPLASH_API_KEY = "dhqoHgWxlRu6JNjOIpKX09ZROXvZtfEQXtOVoCffJZc"
private const val BASE_URL = "https://api.unsplash.com"

class UnsplashApiClient(
    private val httpClient: HttpClient
) {
    suspend fun getPhotos(page: Int, perPage: Int): List<UnsplashResponse> {
        return httpClient.get {
            url("$BASE_URL/photos")
            parameter("client_id", SPLASH_API_KEY)
            parameter("page", page)
            parameter("per_page", perPage)
        }.body()
    }
}
