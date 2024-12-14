package com.andrespelaezp.datasourcecompiler.di

import com.andrespelaezp.datasourcecompiler.api.google.GoogleTasksService
import com.andrespelaezp.datasourcecompiler.api.jira.JiraService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val JIRA_BASE_URL = "https://andypelele.atlassian.net/"
    private const val GOOGLE_BASE_URL = "https://www.googleapis.com/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideJiraService(): JiraService {
        return provideRetrofit(JIRA_BASE_URL).create(JiraService::class.java)
    }

    @Provides
    @Singleton
    fun provideGoogleTasksService(): GoogleTasksService {
        return provideRetrofit(GOOGLE_BASE_URL).create(GoogleTasksService::class.java)
    }
}
