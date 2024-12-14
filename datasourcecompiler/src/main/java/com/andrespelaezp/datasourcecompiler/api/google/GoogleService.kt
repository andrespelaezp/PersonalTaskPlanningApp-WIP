package com.andrespelaezp.datasourcecompiler.api.google

import com.andrespelaezp.datasourcecompiler.api.data.google.GoogleTasksResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GoogleTasksService {

    @GET("tasks/v1/lists/{taskListId}/tasks")
    suspend fun getTasks(
        @Path("taskListId") taskListId: String,
        @Header("Authorization") auth: String
    ): GoogleTasksResponse

}
