package com.andrespelaezp.datasourcecompiler.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dispatcherModule = module {

    single<CoroutineDispatcher> {
        Dispatchers.IO
    }

    single<CoroutineDispatcher> {
        Dispatchers.Default
    }

}