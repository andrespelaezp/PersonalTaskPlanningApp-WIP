package com.andrespelaezp.datasourcecompiler.api.jira

import com.andrespelaezp.datasourcecompiler.api.data.jira.JiraTaskResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface JiraService {

    @GET("rest/api/3/search")
    suspend fun getTasks(
        @Query("jql") jql: String,
        @Header("Authorization") auth: String
    ): JiraTaskResponse

}