package com.andrespelaezp.datasourcecompiler.di

import com.andrespelaezp.datasourcecompiler.api.google.GoogleTasksService
import com.andrespelaezp.datasourcecompiler.api.jira.JiraService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val JIRA_BASE_URL = "https://andypelele.atlassian.net/"
private const val GOOGLE_BASE_URL = "https://www.googleapis.com/"

val networkModule = module {

    single<JiraService> {
        Retrofit.Builder()
            .baseUrl(JIRA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JiraService::class.java)
    }
    single<GoogleTasksService> {
        Retrofit.Builder()
            .baseUrl(GOOGLE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GoogleTasksService::class.java)
    }
}