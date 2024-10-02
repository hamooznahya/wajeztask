package com.example.wajeztask.domain.usecase

import com.example.wajeztask.domain.model.Wizards
import com.example.wajeztask.domain.repository.WizardsRepository
import com.example.wajeztask.utils.ResponseState

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWizardsListUseCase @Inject constructor(
    private val userRepository: WizardsRepository
) {

    fun execute(firstName: String, lastName: String): Flow<ResponseState<List<Wizards>>> {
        return userRepository.getWizardsList(firstName, lastName)
    }
    fun execute3(): Flow<List<Wizards>> {
        return userRepository.getAllDataFromCache()
    }
}