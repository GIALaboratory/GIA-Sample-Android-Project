package com.example.data.utils

import android.content.Context
import com.example.data.remote.models.ReportResponse
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.io.IOException

/**
 * Synchronous version of MockResponseLoader for use in interceptors.
 * This allows JSON fixtures to be loaded without coroutines.
 */
object SyncMockResponseLoader {

    private val gson = Gson()

    /**
     * Load mock report response synchronously
     */
    fun loadMockReportResponse(context: Context, reportNumber: String): ReportResponse {
        return try {
            val jsonString = loadJsonFromAssets(context, "mock_responses.json")
            val jsonObject = gson.fromJson(jsonString, JsonObject::class.java)

            val report = jsonObject.getAsJsonObject(reportNumber)
            gson.fromJson(report, ReportResponse::class.java)
        } catch (e: Exception) {
            throw IOException("Failed to load mock report response: ${e.message}")
        }
    }

    private fun loadJsonFromAssets(context: Context, fileName: String): String {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            throw IOException("Failed to load JSON file: $fileName")
        }
    }
} 