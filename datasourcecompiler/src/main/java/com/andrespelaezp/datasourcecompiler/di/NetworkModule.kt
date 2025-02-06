package com.andrespelaezp.datasourcecompiler.di

import com.andrespelaezp.datasourcecompiler.api.google.GoogleTasksService
import com.andrespelaezp.datasourcecompiler.api.jira.JiraService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val JIRA_BASE_URL = "https://andypelele.atlassian.net/"
private const val GOOGLE_BASE_URL = "https://www.googleapis.com/"

val networkModule = module {

    single<OkHttpClient> {
        val intercepter = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder().apply {
            this.addInterceptor(intercepter)
                // time out setting
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(25,TimeUnit.SECONDS)

        }.build()
    }
    single<JiraService> {
        Retrofit.Builder()
            .baseUrl(JIRA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
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