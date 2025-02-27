package com.andrespelaezp.datasourcecompiler.data.mappers

import com.andrespelaezp.datasourcecompiler.data.mocks.TaskMocks.openProjectTaskListMock
import com.andrespelaezp.datasourcecompiler.data.mocks.TaskMocks.workPackagesListResponseMock
import com.andrespelaezp.datasourcecompiler.data.repository.mapper.mapOpenProjectTask
import org.junit.Test

class TaskMapperTest {

    @Test
    fun mappingOpenProjectTask() {
        val inputTask = workPackagesListResponseMock
        val result = mapOpenProjectTask(inputTask)
        val expectedTask = openProjectTaskListMock
        assert(result.size == expectedTask.size)
        assert(result[0].id == expectedTask[0].id)
        assert(result[0].title == expectedTask[0].title)
        assert(result[0].status == expectedTask[0].status)
        assert(result[0].summary == expectedTask[0].summary)
        assert(result[0].createdAt == expectedTask[0].createdAt)
        assert(result[0].updatedAt == expectedTask[0].updatedAt)
        assert(result[0].sourceType == expectedTask[0].sourceType)
        assert(result[0].lockVersion == expectedTask[0].lockVersion)
        assert(result[0].isSynced == expectedTask[0].isSynced)
    }

}