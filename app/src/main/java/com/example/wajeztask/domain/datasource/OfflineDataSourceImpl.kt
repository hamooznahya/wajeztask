package com.example.wajeztask.domain.datasource


import com.example.wajeztask.data.datasource.OfflineDataSource
import com.example.wajeztask.domain.model.Wizards
import com.example.wajeztask.room.WizardsDao
import com.example.wajeztask.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class OfflineDataSourceImpl(
    private val wizardsDao: WizardsDao
) : OfflineDataSource {
    override suspend fun insertAll(list: List<Wizards>)  {
        wizardsDao.insertAll(list)
    }

    override  fun getAllWizards():  Flow<List<Wizards> >{
     return   wizardsDao.getAllWizards()
    }

    override suspend fun deleteAll() {
        wizardsDao.deleteAll()
    }


}