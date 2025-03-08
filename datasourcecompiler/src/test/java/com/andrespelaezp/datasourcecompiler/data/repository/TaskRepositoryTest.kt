package com.andrespelaezp.datasourcecompiler.data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.andrespelaezp.datasourcecompiler.data.SourceType
import com.andrespelaezp.datasourcecompiler.data.Status
import com.andrespelaezp.datasourcecompiler.data.Task
import com.andrespelaezp.datasourcecompiler.data.dao.TaskDao
import com.andrespelaezp.datasourcecompiler.database.TaskDatabase
import com.andrespelaezp.datasourcecompiler.data.mocks.FakeApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class TaskRepositoryTest {

    private lateinit var database: TaskDatabase
    private lateinit var workPackageDao: TaskDao
    private lateinit var repository: TaskRepository
    private lateinit var fakeApiService: FakeApiService
    @Before
    fun setup() {
        database = run {
            val context = ApplicationProvider.getApplicationContext<Context>()
            Room.inMemoryDatabaseBuilder(
                context,
                TaskDatabase::class.java,
            ).build()
        }
        workPackageDao = database.taskDao()
        fakeApiService = FakeApiService()
        repository = TaskRepository(fakeApiService, workPackageDao)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        database.close()
        Dispatchers.resetMain()
    }

    @Test
    fun `insert and retrieve work packages`() = runBlocking {
        val workPackage = Task("1", "Test Work Package", "Test Description", SourceType.OPEN_PROJECT, Status.NEW, "2024-02-26T12:00:00Z")

        withContext(Dispatchers.IO) {

            workPackageDao.insertAll(listOf(workPackage))

            val result = workPackageDao.getAllWorkPackages()

            assertEquals(1, result.size)
            assertEquals("Test Description", result.first().summary)
        }
    }

    @Test
    fun `detect unsynced work packages`() = runBlocking {
        val unsyncedPackage = Task("2", "Unsynced Package", null, SourceType.OPEN_PROJECT, Status.NEW, "2024-02-26T12:00:00Z")
        withContext(Dispatchers.IO) {
            workPackageDao.insertAll(listOf(unsyncedPackage))

            val unsyncedList = workPackageDao.getUnsyncedWorkPackages()
            assertEquals(1, unsyncedList.size)
            assertFalse(unsyncedList.first().isSynced)
        }
    }
}