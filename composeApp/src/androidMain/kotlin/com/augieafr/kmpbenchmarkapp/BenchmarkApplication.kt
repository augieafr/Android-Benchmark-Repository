package com.augieafr.kmpbenchmarkapp

import android.app.Application
import com.augieafr.kmpbenchmarkapp.di.modules.initKoin
import org.koin.android.ext.koin.androidContext

class BenchmarkApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BenchmarkApplication)
        }
    }
}