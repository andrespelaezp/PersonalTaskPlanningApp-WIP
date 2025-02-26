package com.andrespelaezp.datasourcecompiler.di

import com.andrespelaezp.datasourcecompiler.repository.TaskRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<TaskRepository> {
        TaskRepository(get(), get(), get())
    }
}