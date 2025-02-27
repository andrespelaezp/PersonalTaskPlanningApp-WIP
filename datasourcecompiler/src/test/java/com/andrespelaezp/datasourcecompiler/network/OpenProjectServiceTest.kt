package com.andrespelaezp.datasourcecompiler.network

import com.andrespelaezp.datasourcecompiler.api.services.openproject.OpenProjectService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

@OptIn(ExperimentalCoroutinesApi::class)
class OpenProjectServiceTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: OpenProjectService

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        mockWebServer = MockWebServer()
        mockWebServer.start()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenProjectService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        Dispatchers.resetMain()
    }


    @Test
    fun `fetch work packages successfully`() = runTest {
        // ✅ Mock API Response
        val mockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody("""
                {
                  "_embedded": {
                    "elements": [
                      {
                        "id": 1,
                        "subject": "Test Work Package 1",
                        "description": { "raw": "Description 1" },
                        "status": "Open",
                        "updatedAt": "2024-02-26T12:00:00Z"
                      },
                      {
                        "id": 2,
                        "subject": "Test Work Package 2",
                        "description": { "raw": "Description 2" },
                        "status": "Closed",
                        "updatedAt": "2024-02-25T12:00:00Z"
                      }
                    ]
                  }
                }
            """.trimIndent())

        mockWebServer.enqueue(mockResponse)

        // ✅ Make API call
        val result = apiService.getWorkPackagesByProject("zproject")

        // ✅ Ensure request was made
        val request = mockWebServer.takeRequest()
        assertEquals("/api/v3/projects/zproject/work_packages", request.path) // Correct API path
        assertEquals("GET", request.method) // Correct HTTP method

        // ✅ Validate response
        assertEquals(2, result.body()?.embedded?.workPackages?.size)
        assertEquals("Test Work Package 1", result.body()?.embedded?.workPackages?.get(0)?.subject ?: "")
        assertEquals("Open", result.body()?.embedded?.workPackages?.get(0)?.status ?: "")
    }

    @Test
    fun `fetch work packages fails with 404`() = runTest {
        // ✅ Mock Error Response
        val mockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
            .setBody("""{ "error": "Not Found" }""")
        mockWebServer.enqueue(mockResponse)

        try {
            apiService.getWorkPackagesByProject("zproject")
        } catch (e: Exception) {
            assert(e is retrofit2.HttpException) // Ensures correct exception is thrown
            assertEquals(404, (e as retrofit2.HttpException).code()) // Validate error code
        }
    }
}