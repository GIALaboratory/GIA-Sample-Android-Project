package com.example.data.remote.interceptors

import android.content.Context
import com.example.data.utils.SyncMockResponseLoader
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException
import javax.inject.Inject

/**
 * Retrofit interceptor that serves JSON fixtures instead of making real network calls.
 * This allows you to use JSON fixtures with standard Retrofit services.
 */
class MockInterceptor @Inject constructor(
    private val context: Context
) : Interceptor {

    companion object {
        private const val CONTENT_TYPE = "application/json"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()

        return when {
            // Handle report fetch requests
            url.contains("reportno=") -> {
                val reportNumber = extractReportNumber(url)
                serveMockReportResponse(reportNumber)
            }
            // Default: pass through to real network
            else -> {
                chain.proceed(request)
            }
        }
    }

    private fun extractReportNumber(url: String): String {
        val reportNoParam = "reportno="
        val startIndex = url.indexOf(reportNoParam)
        return if (startIndex != -1) {
            val endIndex = url.indexOf("&", startIndex)
            if (endIndex != -1) {
                url.substring(startIndex + reportNoParam.length, endIndex)
            } else {
                url.substring(startIndex + reportNoParam.length)
            }
        } else {
            "unknown"
        }
    }

    private fun serveMockReportResponse(reportNumber: String): Response {
        return try {
            val mockResponse = SyncMockResponseLoader.loadMockReportResponse(context, reportNumber)
            val jsonResponse = com.google.gson.Gson().toJson(mockResponse)

            Response.Builder()
                .code(200)
                .message("OK")
                .protocol(Protocol.HTTP_1_1)
                .request(okhttp3.Request.Builder().url("https://mock-api.gia.edu").build())
                .body(jsonResponse.toResponseBody(CONTENT_TYPE.toMediaType()))
                .build()
        } catch (e: Exception) {
            // Return 404 if fixture not found
            Response.Builder()
                .code(404)
                .message("Fixture not found")
                .protocol(Protocol.HTTP_1_1)
                .request(okhttp3.Request.Builder().url("https://mock-api.gia.edu").build())
                .body("Fixture not found for report: $reportNumber".toResponseBody("text/plain".toMediaType()))
                .build()
        }
    }
} 