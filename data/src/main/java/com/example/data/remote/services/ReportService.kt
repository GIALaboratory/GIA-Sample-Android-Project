package com.example.data.remote.services

import com.example.data.remote.models.ReportResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query
import retrofit2.http.Url

interface ReportService {

    companion object {
        const val REPORT_NUMBER_PARAM = "reportno"
    }

    @GET(".")
    suspend fun fetchReportBy(
        @Query(REPORT_NUMBER_PARAM) reportNumber: String,
    ): Response<ReportResponse>
}