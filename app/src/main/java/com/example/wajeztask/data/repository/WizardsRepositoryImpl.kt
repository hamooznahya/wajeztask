package com.example.wajeztask.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.wajeztask.data.datasource.OfflineDataSource
import com.example.wajeztask.data.datasource.RemoteDataSource
import com.example.wajeztask.data.dto.ElixirsResponse
import com.example.wajeztask.dispatcher.IDispatchers
import com.example.wajeztask.domain.mappers.WizardsMapper
import com.example.wajeztask.domain.model.Elixirs
import com.example.wajeztask.domain.model.Wizards
import com.example.wajeztask.domain.repository.WizardsRepository
import com.example.wajeztask.room.WizardsDao
import com.example.wajeztask.utils.ResponseState
import com.example.wajeztask.utils.mapToError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class WizardsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private var dispatcher: IDispatchers,
    private var mapper: WizardsMapper,
    private val offlineDataSource: OfflineDataSource


) : WizardsRepository {
    private val workerDispatcher = dispatcher.workerDispatcher()

    override fun getWizardsList(firstName: String,lastName: String): Flow<ResponseState<List<Wizards>>> {
        return flow {
            emit(ResponseState.Loading)
            remoteDataSource.getWizardsList(firstName,lastName).onSuccess {
                val itemList=mapper.mapList(it)
                offlineDataSource.deleteAll()
                offlineDataSource.insertAll(itemList)
                emit(ResponseState.Success(itemList))
            }.onFailure {
                emit(ResponseState.Failure(it.mapToError()))
            }
        }.flowOn(workerDispatcher)
    }

    override fun getAllDataFromCache():   Flow<List<Wizards>> {
        return offlineDataSource.getAllWizards()
         }

    override fun getWizardsDetails(id: String): Flow<ResponseState<Wizards>> {
        return flow {
            emit(ResponseState.Loading)
            remoteDataSource.getWizardsDetails(id).onSuccess {
                emit(ResponseState.Success(mapper.map(it)))
            }.onFailure {
                emit(ResponseState.Failure(it.mapToError()))
            }
        }.flowOn(workerDispatcher)
    }

    override fun getElixirsDetails(id: String): Flow<ResponseState<Elixirs>> {
        return flow {
            emit(ResponseState.Loading)
            remoteDataSource.getElixirsDetails(id).onSuccess {
                emit(ResponseState.Success(mapper.mapElixirs(it)))
            }.onFailure {
                emit(ResponseState.Failure(it.mapToError()))
            }
        }.flowOn(workerDispatcher)
    }

}