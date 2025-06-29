package com.augieafr.benchmarkapp.utils

import com.augieafr.benchmarkapp.ui.navigation.route.MainRoute

val MainRoute.route: String?
    get() = this::class.qualifiedName
