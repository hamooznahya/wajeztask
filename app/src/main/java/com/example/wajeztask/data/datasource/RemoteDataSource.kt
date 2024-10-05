package com.example.wajeztask.data.datasource

import com.example.wajeztask.data.dto.ElixirsResponse
import com.example.wajeztask.data.dto.WizardsResponse


interface RemoteDataSource {
    suspend fun getWizardsList(firstName: String, lastName: String): Result<List<WizardsResponse>>
    suspend fun getWizardsDetails(id: String): Result<WizardsResponse>
    suspend fun getElixirsDetails(id: String): Result<ElixirsResponse>

}