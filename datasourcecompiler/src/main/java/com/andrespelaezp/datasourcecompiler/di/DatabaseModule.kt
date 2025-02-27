package com.andrespelaezp.datasourcecompiler.di

import androidx.room.Room
import com.andrespelaezp.datasourcecompiler.data.dao.TaskDao
import com.andrespelaezp.datasourcecompiler.database.TaskDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single<TaskDatabase> {
        Room.databaseBuilder(
            androidContext(),
            TaskDatabase::class.java,
            "task-database",
        ).build()
    }

    single<TaskDao> { get<TaskDatabase>().taskDao() }

}