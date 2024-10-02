package com.example.wajeztask.domain.datasource

import com.example.wajeztask.data.APIEndpoints
import com.example.wajeztask.data.datasource.RemoteDataSource
import com.example.wajeztask.data.dto.ElixirsResponse
import com.example.wajeztask.data.dto.WizardsResponse

import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiEndpoints: APIEndpoints
) : RemoteDataSource {
    override suspend fun getWizardsList(
        firstName: String,
        lastName: String
    ): Result<List<WizardsResponse>> {
        return runCatching {
            apiEndpoints.getWizardsList(firstName, lastName)
        }
    }
    override suspend fun getWizardsDetails(
        id: String
    ): Result<WizardsResponse> {
        return runCatching {
            apiEndpoints.getWizardsDetails(id)
        }
    }
    override suspend fun getElixirsDetails(
        id: String
    ): Result<ElixirsResponse> {
        return runCatching {
            apiEndpoints.getElixirsDetails(id)
        }
    }
}