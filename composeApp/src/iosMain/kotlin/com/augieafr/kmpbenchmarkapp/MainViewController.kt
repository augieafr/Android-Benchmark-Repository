package com.augieafr.kmpbenchmarkapp

import androidx.compose.ui.window.ComposeUIViewController
import com.augieafr.kmpbenchmarkapp.di.modules.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}