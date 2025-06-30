package com.augieafr.kmpbenchmarkapp.di.modules

import com.augieafr.kmpbenchmarkapp.data.local.room.getDatabaseBuilder
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { getDatabaseBuilder(get()) }
    single<HttpClientEngine> { OkHttp.create() }
}