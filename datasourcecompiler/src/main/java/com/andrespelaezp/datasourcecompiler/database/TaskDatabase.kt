package com.andrespelaezp.datasourcecompiler.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.andrespelaezp.datasourcecompiler.data.Task
import com.andrespelaezp.datasourcecompiler.data.dao.TaskDao
import com.andrespelaezp.datasourcecompiler.database.converters.InstantConverter
import com.andrespelaezp.datasourcecompiler.database.converters.TaskTypeConverter

@Database(
    entities = [
        Task::class,
    ],
    version = 1,
    autoMigrations = [
    ],
    exportSchema = true,
)
@TypeConverters(
    InstantConverter::class,
    TaskTypeConverter::class
)
internal abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
