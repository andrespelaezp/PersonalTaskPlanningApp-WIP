package com.andrespelaezp.datasourcecompiler.data.mocks

import com.andrespelaezp.datasourcecompiler.api.data.openproject.Description
import com.andrespelaezp.datasourcecompiler.api.data.openproject.EmbeddedWorkPackages
import com.andrespelaezp.datasourcecompiler.api.data.openproject.WorkPackage
import com.andrespelaezp.datasourcecompiler.api.data.openproject.WorkPackagesListResponse
import com.andrespelaezp.datasourcecompiler.data.SourceType
import com.andrespelaezp.datasourcecompiler.data.Status
import com.andrespelaezp.datasourcecompiler.data.Task

object TaskMocks {

    val workPackageMock = WorkPackage(
        id = 1,
        subject = "Test Work Package 1",
        description = Description(raw = "Description 1"),
        status = "NEW",
        dueDate = "2021-12-31",
        createdAt = "2021-01-01",
        updatedAt = "2021-01-01",
        lockVersion = 0
    )

    val workPackageMock2 = WorkPackage(
        id = 2,
        subject = "Test Work Package 2",
        description = Description(raw = "Description 2"),
        status = "Closed",
        dueDate = "2021-12-31",
        createdAt = "2021-01-01",
        updatedAt = "2021-01-01",
        lockVersion = 0
    )

    val workPackagesListResponseMock = WorkPackagesListResponse(
        total = 2,
        embedded = EmbeddedWorkPackages(
            workPackages = listOf(workPackageMock, workPackageMock2)
        )
    )

    val openProjectTaskMock = Task(
        id = "1",
        title = "Test Work Package 1",
        summary = "Description 1",
        sourceType = SourceType.OPEN_PROJECT,
        status = Status.NEW,
        dueDate = "2021-12-31",
        createdAt = "2021-01-01",
        updatedAt = "2021-01-01",
        lockVersion = 0,
        isSynced = false,
    )

    val openProjectTaskMock2 = Task(
        id = "1",
        title = "Test Work Package 2",
        summary = "Description 2",
        sourceType = SourceType.OPEN_PROJECT,
        status = Status.NEW,
        dueDate = "2021-12-31",
        createdAt = "2021-01-01",
        updatedAt = "2021-01-01",
        lockVersion = 0,
        isSynced = false,
    )

    val openProjectTaskListMock = listOf(openProjectTaskMock, openProjectTaskMock2)
}