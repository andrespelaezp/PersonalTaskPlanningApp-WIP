package com.andrespelaezp.datasourcecompiler.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.andrespelaezp.datasourcecompiler.data.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(workPackages: List<Task>)

    @Query("SELECT * FROM tasks ORDER BY id DESC")
    suspend fun getAllWorkPackages(): List<Task>

    @Query("SELECT * FROM tasks WHERE isSynced = 0")
    suspend fun getUnsyncedWorkPackages(): List<Task>

    @Update
    suspend fun updateWorkPackage(workPackage: Task)
}