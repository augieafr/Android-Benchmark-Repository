package com.augieafr.kmpbenchmarkapp.ui.navigation.route

import kotlinx.serialization.Serializable

sealed class MainRoute {
    @Serializable
    data object BenchmarkOption : MainRoute()

    @Serializable
    data object ScrollTest : MainRoute()

    @Serializable
    data object DatabaseOperationTest : MainRoute()

    @Serializable
    data object AnimationTest : MainRoute()
}

val MainRoute.route: String?
    get() = this::class.qualifiedName
