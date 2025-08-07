package com.example.data.remote

import com.example.common.NetResult
import com.example.data.remote.services.ReportService
import com.example.domain.model.Report
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReportRemoteSource @Inject constructor(
    private val reportService: ReportService,
) {

    fun fetchReportBy(reportNumber: String): Flow<NetResult<Report>> = flow {
        val response = reportService.fetchReportBy(reportNumber = reportNumber)
        emit(response)
    }
        .catch { exception ->
            exception.printStackTrace()
        }
        .map { res ->
            try {
                val report = res.parseThrowable {
                    if (it.report != null) {
                        it.report.parse()
                    } else {
                        throw Exception(it.errorCode!!)
                    }
                }
                if (report != null) {
                    NetResult.Success(report)
                } else {
                    NetResult.Error(Throwable("Report not found"), 404)
                }
            } catch(e: Exception) {
                NetResult.Error(Throwable(e.message), e.message!!.toInt(), e)
            }
        }
        .flowOn(Dispatchers.IO)
}