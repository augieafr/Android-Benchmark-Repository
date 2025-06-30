package com.augieafr.kmpbenchmarkapp.di.modules

import com.augieafr.kmpbenchmarkapp.data.api_interface.UnsplashApiClient
import com.augieafr.kmpbenchmarkapp.data.local.room.AppDatabase
import com.augieafr.kmpbenchmarkapp.data.local.room.getRoomDatabase
import com.augieafr.kmpbenchmarkapp.data.repository.NoteRepository
import com.augieafr.kmpbenchmarkapp.data.repository.UnsplashRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient(get()) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                })
            }
        }
    }

    single {
        UnsplashApiClient(get())
    }
}
val databaseModule = module {
    single { getRoomDatabase(get()) }
    single { get<AppDatabase>().noteDao() }
}

val repositoryModule = module {
    single { NoteRepository(get()) }
    single { UnsplashRepository(get()) }
}