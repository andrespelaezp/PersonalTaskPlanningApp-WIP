package com.andrespelaezp.datasourcecompiler.data.mocks

import com.andrespelaezp.datasourcecompiler.api.data.openproject.EmbeddedWorkPackages
import com.andrespelaezp.datasourcecompiler.api.data.openproject.WorkPackage
import com.andrespelaezp.datasourcecompiler.api.data.openproject.WorkPackagesListResponse
import com.andrespelaezp.datasourcecompiler.api.services.openproject.OpenProjectService
import com.andrespelaezp.datasourcecompiler.data.mocks.TaskMocks.workPackageMock
import com.andrespelaezp.datasourcecompiler.data.mocks.TaskMocks.workPackageMock2
import retrofit2.Response

class FakeApiService : OpenProjectService {
    private val fakeWorkPackages = mutableListOf<WorkPackage>()

    // Helper function to add test data
    private fun addFakeWorkPackages(workPackages: List<WorkPackage>) {
        fakeWorkPackages.addAll(workPackages)
    }

    override suspend fun getWorkPackagesByProject(projectId: String): Response<WorkPackagesListResponse> {
        addFakeWorkPackages(listOf(workPackageMock, workPackageMock2))

        val response = WorkPackagesListResponse(
            total = 2,
            embedded = EmbeddedWorkPackages(
                workPackages = fakeWorkPackages
            )
        )
        return Response.success(response)
    }

    override suspend fun updateWorkPackage(
        workPackageId: String,
        workPackage: WorkPackage
    ): Response<WorkPackage> {
        TODO("Not yet implemented")
    }

}