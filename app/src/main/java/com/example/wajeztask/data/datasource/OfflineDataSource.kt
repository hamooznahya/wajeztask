package com.example.wajeztask.data.datasource

import com.example.wajeztask.domain.model.Wizards
import com.example.wajeztask.utils.ResponseState
import kotlinx.coroutines.flow.Flow

interface OfflineDataSource {
    suspend fun insertAll(list: List<Wizards>)
     fun getAllWizards() : Flow<List<Wizards>>
    suspend fun deleteAll()
}


