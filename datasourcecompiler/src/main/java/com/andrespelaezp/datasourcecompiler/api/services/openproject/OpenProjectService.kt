package com.andrespelaezp.datasourcecompiler.api.services.openproject

import com.andrespelaezp.datasourcecompiler.api.data.openproject.WorkPackagesListResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface OpenProjectService {

    @GET("api/v3/projects/{id}/work_packages")
    suspend fun getWorkPackagesByProject(
        @Path("id") projectId: String
    ): WorkPackagesListResponse

}
