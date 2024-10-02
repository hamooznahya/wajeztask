package com.example.wajeztask.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.wajeztask.data.datasource.RemoteDataSource
import com.example.wajeztask.dispatcher.IDispatchers
import com.example.wajeztask.domain.mappers.WizardsMapper
import com.example.wajeztask.domain.model.Wizards
import com.example.wajeztask.domain.repository.WizardsRepository
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

    ) : WizardsRepository {
    private val workerDispatcher = dispatcher.workerDispatcher()

    override fun getWizardsList(firstName: String,lastName: String): Flow<ResponseState<List<Wizards>>> {
        return flow {
            emit(ResponseState.Loading)
            remoteDataSource.getWizardsList(firstName,lastName).onSuccess {
                emit(ResponseState.Success(mapper.mapList(it)))
            }.onFailure {
                emit(ResponseState.Failure(it.mapToError()))
            }
        }.flowOn(workerDispatcher)
    }

}