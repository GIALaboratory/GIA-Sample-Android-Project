package com.example.common

sealed class NetResult<out T> {

    object StartLoading : NetResult<Nothing>()

    object StopLoading : NetResult<Nothing>()

    data class Success<out T>(val data: T) : NetResult<T>()

    data class Error(val error: Throwable, val code: Int, val e: Exception? = null) : NetResult<Nothing>() {
    }
}