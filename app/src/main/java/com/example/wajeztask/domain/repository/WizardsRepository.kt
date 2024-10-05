package com.example.wajeztask.domain.repository

import com.example.wajeztask.domain.model.Elixirs
import com.example.wajeztask.data.entities.WizardsEntity
import com.example.wajeztask.domain.model.Wizard
import com.example.wajeztask.utils.ResponseState

import kotlinx.coroutines.flow.Flow

interface WizardsRepository {

    fun getWizardsList(firstName: String,lastName: String) : Flow<ResponseState<List<Wizard>>>
    fun getAllDataFromCache():  Flow<List<WizardsEntity>>
    fun getWizardsDetails(id: String) : Flow<ResponseState<Wizard>>
    fun getElixirsDetails(id: String) : Flow<ResponseState<Elixirs>>

}