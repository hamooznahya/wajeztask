package com.example.wajeztask.data.repository

import com.example.wajeztask.data.datasource.OfflineDataSource
import com.example.wajeztask.data.datasource.RemoteDataSource
import com.example.wajeztask.dispatcher.IDispatchers
import com.example.wajeztask.domain.mappers.WizardsMapper
import com.example.wajeztask.domain.model.Elixirs
import com.example.wajeztask.data.entities.WizardsEntity
import com.example.wajeztask.domain.model.Wizard
import com.example.wajeztask.domain.repository.WizardsRepository
import com.example.wajeztask.utils.ResponseState
import com.example.wajeztask.utils.mapToError
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
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

    override fun getWizardsList(firstName: String,lastName: String): Flow<ResponseState<List<Wizard>>> {
        return flow<ResponseState<List<Wizard>>> {
            emit(ResponseState.Loading)
            val wizardList = offlineDataSource.getAllWizards().firstOrNull()
                ?.map { mapper.mapWizardsEntityToModel(it) }.orEmpty()
            emit(ResponseState.Success(wizardList))

            remoteDataSource.getWizardsList(firstName,lastName).onSuccess {
                val itemList = mapper.mapList(it)
                offlineDataSource.insertAll(itemList)
                val wizardList = offlineDataSource.getAllWizards().firstOrNull()
                    ?.map { mapper.mapWizardsEntityToModel(it) }.orEmpty()
                emit(ResponseState.Success(wizardList))
            }.onFailure {
                emit(ResponseState.Failure(it.mapToError()))
            }
        }.flowOn(workerDispatcher)
    }

    override fun getAllDataFromCache():   Flow<List<WizardsEntity>> {
        return offlineDataSource.getAllWizards()
         }


    override fun getWizardsDetails(id: String): Flow<ResponseState<Wizard>> {
        return flow {
            emit(ResponseState.Loading)
            remoteDataSource.getWizardsDetails(id).onSuccess {
                emit(ResponseState.Success(mapper.mapWizardDetails(it)))
            }.onFailure {
                emit(ResponseState.Failure(it.mapToError()))
            }
        }.flowOn(workerDispatcher)
    }

    override fun getElixirsDetails(id: String): Flow<ResponseState<Elixirs>> {
        return flow {
            emit(ResponseState.Loading)
            remoteDataSource.getElixirsDetails(id).onSuccess {
                emit(ResponseState.Success(mapper.mapElixirsDetails(it)))
            }.onFailure {
                emit(ResponseState.Failure(it.mapToError()))
            }
        }.flowOn(workerDispatcher)
    }

}