package com.example.dstvmovie.compose.ui.base

import com.example.dstvmovie.data.remote.BaseException

sealed class ErrorEvent(
    val type: ErrorType,
    val baseException: BaseException? = null
) {
    object Network : ErrorEvent(type = ErrorType.NETWORK)
    object Timeout : ErrorEvent(type = ErrorType.TIMEOUT)
    object Unauthorized : ErrorEvent(type = ErrorType.HTTP_UNAUTHORIZED)
    class Unknown(baseException: BaseException) :
        ErrorEvent(type = ErrorType.UNKNOWN, baseException = baseException)
}

enum class ErrorType {
    NETWORK,
    TIMEOUT,
    HTTP_UNAUTHORIZED,
    FORCE_UPDATE,
    UNKNOWN
}
