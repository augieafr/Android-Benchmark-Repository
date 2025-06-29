package com.augieafr.kmpbenchmarkapp.utils

import com.augieafr.kmpbenchmarkapp.ui.navigation.route.MainRoute

val MainRoute.route: String?
    get() = this::class.qualifiedName
