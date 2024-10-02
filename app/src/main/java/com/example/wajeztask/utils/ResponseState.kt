package com.example.wajeztask.utils

import com.example.wajeztask.domain.model.WajeezError


sealed class ResponseState<out T> {
    object Loading : ResponseState<Nothing>()
    data class Failure(val error: WajeezError) : ResponseState<Nothing>()
    data class Success<T>(val item: T) : ResponseState<T>()

}


