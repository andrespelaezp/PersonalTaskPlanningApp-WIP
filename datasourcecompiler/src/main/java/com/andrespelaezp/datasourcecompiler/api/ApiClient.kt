package com.andrespelaezp.datasourcecompiler.api

import com.andrespelaezp.datasourcecompiler.api.google.GoogleTasksService
import com.andrespelaezp.datasourcecompiler.api.jira.JiraService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val JIRA_BASE_URL = "https://andypelele.atlassian.net/"
    private const val GOOGLE_BASE_URL = "https://www.googleapis.com/"

    private fun createRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getJiraService(): JiraService {
        return createRetrofit(JIRA_BASE_URL).create(JiraService::class.java)
    }

    fun getGoogleTasksService(): GoogleTasksService {
        return createRetrofit(GOOGLE_BASE_URL).create(GoogleTasksService::class.java)
    }

}