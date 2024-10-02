package com.example.wajeztask.utils

import com.example.wajeztask.data.network.ServerException
import com.example.wajeztask.domain.model.WajeezError
import com.google.android.gms.tasks.Task
import com.google.gson.JsonSyntaxException
import com.google.gson.stream.MalformedJsonException
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLHandshakeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


fun Throwable.mapToError(): WajeezError {
    return when (this) {
        is HttpException -> {
            (WajeezError.createServerError(this.code().toString(), this.message()))
        }
        is ServerException -> {
            (WajeezError.createServerError(this.code.toString(), this.message.orEmpty()))
        }
        is SocketTimeoutException -> {
            (WajeezError.ERROR_TIME_OUT)
        }
        is SSLHandshakeException -> {
            WajeezError.createGenericError("123", "SSLHandshakeException")
        }
        is MalformedJsonException, is IllegalArgumentException, is IllegalStateException, is JsonSyntaxException -> {
            (WajeezError.createGenericError("123", "ERROR_CANNOT_PARSE_JSON"))
        }
        is IOException -> {
            (WajeezError.ERROR_NO_INTERNET_CONNECTION)
        }
        is Exception -> {
            (WajeezError.createGenericError("123", this.message.orEmpty()))
        }

        else -> WajeezError.ERROR_INTERNAL_ERROR
    }


}
suspend fun <T> Task<T>.await(): T = suspendCancellableCoroutine { continuation ->
    addOnCompleteListener { task ->
        if (task.isSuccessful) {
            continuation.resume(task.result)
        } else {
            continuation.resumeWithException(
                task.exception ?: RuntimeException("Unknown task exception")
            )
        }
    }
}


