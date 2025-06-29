package com.augieafr.kmpbenchmarkapp.di.modules

import com.augieafr.kmpbenchmarkapp.data.local.room.AppDatabase
import com.augieafr.kmpbenchmarkapp.data.local.room.getRoomDatabase
import com.augieafr.kmpbenchmarkapp.data.repository.NoteRepository
import org.koin.dsl.module

val databaseModule = module {
    single { getRoomDatabase(get()) }
    single { get<AppDatabase>().noteDao() }
}

val repositoryModule = module {
    single { NoteRepository(get()) }
}