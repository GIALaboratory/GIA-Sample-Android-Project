package com.example.data.repository

import com.example.common.NetResult
import com.example.data.remote.ReportRemoteSource
import com.example.data.remote.loading
import com.example.domain.model.Report
import com.example.domain.repository.ReportRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReportRepositoryImpl @Inject constructor(
    private val reportRemoteSource: ReportRemoteSource
) : ReportRepository {

    override fun fetchReportBy(reportNum: String): Flow<NetResult<Report>> {
        val reportNumber = reportNum.replace("-", "")
        
        val service = reportRemoteSource.fetchReportBy(reportNumber)
        
        return service.loading().map { res ->
            if (res is NetResult.Success) {
                NetResult.Success(res.data)
            } else {
                res
            }
        }
    }
}