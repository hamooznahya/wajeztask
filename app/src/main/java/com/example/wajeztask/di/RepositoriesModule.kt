package com.example.wajeztask.di


import com.example.wajeztask.data.datasource.OfflineDataSource
import com.example.wajeztask.data.datasource.RemoteDataSource
import com.example.wajeztask.data.repository.WizardsRepositoryImpl
import com.example.wajeztask.dispatcher.IDispatchers
import com.example.wajeztask.domain.mappers.WizardsMapper
import com.example.wajeztask.domain.repository.WizardsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RepositoriesModule {




    @Provides
    @Singleton
    fun WizardsRepository(
        remoteDataSource: RemoteDataSource,
        offlineDataSource: OfflineDataSource,
        dispatcher: IDispatchers,
    ): WizardsRepository =
        WizardsRepositoryImpl(
            remoteDataSource,
            dispatcher,
            WizardsMapper,
            offlineDataSource
        )


}