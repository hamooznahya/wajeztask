package com.example.wajeztask.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatchersImpl : IDispatchers {
    override fun workerDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}