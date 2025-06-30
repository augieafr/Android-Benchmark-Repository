package com.augieafr.kmpbenchmarkapp.di.modules

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            platformModule,
            databaseModule,
            repositoryModule,
            viewModelModule,
            networkModule
        )
    }
}