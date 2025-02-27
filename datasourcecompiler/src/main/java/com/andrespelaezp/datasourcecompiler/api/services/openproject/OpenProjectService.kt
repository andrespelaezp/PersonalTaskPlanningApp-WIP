package com.andrespelaezp.datasourcecompiler.api.services.openproject

import com.andrespelaezp.datasourcecompiler.api.data.openproject.WorkPackage
import com.andrespelaezp.datasourcecompiler.api.data.openproject.WorkPackagesListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface OpenProjectService {

    @GET("api/v3/projects/{id}/work_packages")
    suspend fun getWorkPackagesByProject(
        @Path("id") projectId: String
    ): Response<WorkPackagesListResponse>

    @PATCH("api/v3/work_packages/{id}")
    suspend fun updateWorkPackage(
        @Path("id") workPackageId: String,
        @Body workPackage: WorkPackage
    ): Response<WorkPackage>

}
