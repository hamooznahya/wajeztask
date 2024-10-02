package com.example.wajeztask.data.datasource

import com.example.wajeztask.data.dto.WizardsResponse
import com.example.wajeztask.domain.model.Wizards


interface RemoteDataSource {
    suspend fun getWizardsList(firstName: String, lastName: String): Result<List<WizardsResponse>>
    suspend fun getWizardsDetails(id: String): Result<WizardsResponse>

}