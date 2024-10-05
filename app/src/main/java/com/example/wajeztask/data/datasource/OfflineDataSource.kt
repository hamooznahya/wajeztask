package com.example.wajeztask.data.datasource

import com.example.wajeztask.data.entities.WizardsEntity
import kotlinx.coroutines.flow.Flow

interface OfflineDataSource {
    suspend fun insertAll(list: List<WizardsEntity>)
     fun getAllWizards() : Flow<List<WizardsEntity>>
    suspend fun deleteAll()
}


