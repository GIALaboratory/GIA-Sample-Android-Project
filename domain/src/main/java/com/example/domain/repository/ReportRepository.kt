package com.example.domain.repository

import com.example.common.NetResult
import com.example.domain.model.*
import kotlinx.coroutines.flow.Flow

interface ReportRepository {
    fun fetchReportBy(reportNum: String): Flow<NetResult<Report>>
}