package com.andrespelaezp.datasourcecompiler.di

import com.andrespelaezp.datasourcecompiler.api.google.GoogleTasksService
import com.andrespelaezp.datasourcecompiler.api.jira.JiraService
import com.andrespelaezp.datasourcecompiler.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTaskRepository(
        jiraService: JiraService,
        googleTasksService: GoogleTasksService
    ): TaskRepository {
        return TaskRepository(jiraService, googleTasksService)
    }
}