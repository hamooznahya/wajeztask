package com.example.wajeztask.domain.repository

import androidx.paging.PagingData
import com.example.wajeztask.domain.model.Wizards
import com.example.wajeztask.utils.ResponseState

import kotlinx.coroutines.flow.Flow

interface WizardsRepository {

    fun getWizardsList(firstName: String,lastName: String) : Flow<ResponseState<List<Wizards>>>

}