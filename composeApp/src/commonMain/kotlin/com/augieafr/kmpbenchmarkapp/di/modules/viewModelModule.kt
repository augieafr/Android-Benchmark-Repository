package com.augieafr.kmpbenchmarkapp.di.modules

import com.augieafr.kmpbenchmarkapp.ui.screen.database_operation_test.DatabaseOperationTestViewModel
import com.augieafr.kmpbenchmarkapp.ui.screen.scroll_test.ScrollTestViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModelModule = module {
    singleOf(::DatabaseOperationTestViewModel)
    singleOf(::ScrollTestViewModel)
}