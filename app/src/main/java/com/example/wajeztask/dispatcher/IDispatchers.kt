package com.example.wajeztask.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatchers {
    fun workerDispatcher() : CoroutineDispatcher
}

