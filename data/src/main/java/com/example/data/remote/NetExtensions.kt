package com.example.data.remote

import androidx.lifecycle.MutableLiveData
import com.example.common.NetResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

fun <T> Flow<NetResult<T>>.loading(): Flow<NetResult<T>> =
    this.onStart { emit(NetResult.StartLoading) }
        .onCompletion { emit(NetResult.StopLoading) }

suspend inline fun <T, R : NetResult<T>> Flow<R>.collectWithLoading(
    load: MutableLiveData<Boolean>,
    crossinline action: suspend (value: R) -> Unit
) = collect {
    when (it) {
        is NetResult.StartLoading -> load.value = true
        is NetResult.StopLoading -> load.value = false
        else -> action(it)
    }
}

fun <T, R> Response<T>.parse(parseMethod: (T) -> R): NetResult<R> {
    val responseData = body()
    return if (isSuccessful && responseData != null)
        try {
            NetResult.Success(parseMethod(responseData))
        } catch (e: Exception) {
            var code = -1
            if (e is HttpException) {
                code = e.code()
            }
            NetResult.Error(Throwable(e.message), code, e)
        }
    else {
        NetResult.Error(Throwable("Parsing error"), code())
    }
}

fun <T, R> Response<T>.parseThrowable(parseMethod: (T) -> R): R {
    val responseData = body()
    return if (isSuccessful && responseData != null)
        parseMethod(responseData)
    else {
        throw Throwable("Parsing error")
    }
}

fun <T> Throwable.toHumanReadable(): Response<T> {
    return when (this) {
        is UnknownHostException -> Response.error(
            500,
            "Network Connection Unstable".toResponseBody(null)
        )
        else -> Response.error(500, "Oops! Connection problem".toResponseBody(null))
    }
}