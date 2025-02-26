package com.andrespelaezp.datasourcecompiler.di

import com.andrespelaezp.datasourcecompiler.TokenProviderImpl
import com.andrespelaezp.datasourcecompiler.api.AuthInterceptor
import com.andrespelaezp.datasourcecompiler.api.TokenProvider
import com.andrespelaezp.datasourcecompiler.api.services.google.GoogleTasksService
import com.andrespelaezp.datasourcecompiler.api.services.jira.JiraService
import com.andrespelaezp.datasourcecompiler.api.services.openproject.OpenProjectService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val JIRA_BASE_URL = "https://andypelele.atlassian.net/"
private const val GOOGLE_BASE_URL = "https://www.googleapis.com/"
private const val OPEN_PROJECT_BASE_URL = "http://10.0.2.2:3000" // Localhost for Android Emulator
//private const val OPEN_PROJECT_BASE_URL = "http://192.168.1.232:8080/" // Localhost for Home Network

val networkModule = module {
    single<TokenProvider> {
        TokenProviderImpl()
    }
    single<OkHttpClient> {
        OkHttpClient().newBuilder().apply {
            this.addInterceptor(AuthInterceptor(get()))
                .addNetworkInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
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
    single<OpenProjectService> {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .client(get())
            .baseUrl(OPEN_PROJECT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(OpenProjectService::class.java)
    }
}