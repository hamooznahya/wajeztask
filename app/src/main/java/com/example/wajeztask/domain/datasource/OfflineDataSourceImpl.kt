package com.example.wajeztask.domain.datasource


import com.example.wajeztask.data.datasource.OfflineDataSource
import com.example.wajeztask.data.entities.WizardsEntity
import com.example.wajeztask.room.WizardsDao
import kotlinx.coroutines.flow.Flow


class OfflineDataSourceImpl constructor(
    private val wizardsDao: WizardsDao
) : OfflineDataSource {
    override suspend fun insertAll(list: List<WizardsEntity>)  {
        wizardsDao.insertAll(list)
    }

    override  fun getAllWizards():  Flow<List<WizardsEntity> >{
     return   wizardsDao.getAllWizards()
    }

    override suspend fun deleteAll() {
        wizardsDao.deleteAll()
    }


}