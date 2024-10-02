package com.example.wajeztask.domain.model

enum class WajeezError(
    var errorCode: String,
    var errorMessage: String
) {
    ERROR_SERVER_ERROR("", ""),
    ERROR_INTERNAL_ERROR("", ""),
    ERROR_TIME_OUT("5001", "Time out"),
    ERROR_NO_INTERNET_CONNECTION("5003", "please check your internet connection");

    companion object {
        @JvmStatic
        fun createServerError(errorCode: String, errorMessage: String): WajeezError {
            val error = ERROR_SERVER_ERROR
            error.errorMessage = errorMessage
            error.errorCode = errorCode
            return error
        }

        @JvmStatic
        fun createGenericError(responseCode: String, responseMessage: String): WajeezError {
            val error = ERROR_INTERNAL_ERROR
            error.errorCode = responseCode
            error.errorMessage = responseMessage
            return error
        }


    }


}