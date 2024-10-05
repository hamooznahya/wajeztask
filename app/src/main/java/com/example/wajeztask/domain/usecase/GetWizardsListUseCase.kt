package com.example.wajeztask.domain.usecase

import com.example.wajeztask.data.entities.WizardsEntity
import com.example.wajeztask.domain.model.Wizard
import com.example.wajeztask.domain.repository.WizardsRepository
import com.example.wajeztask.utils.ResponseState

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWizardsListUseCase @Inject constructor(
    private val userRepository: WizardsRepository
) {

    fun execute(firstName: String, lastName: String): Flow<ResponseState<List<Wizard>>> {
        return userRepository.getWizardsList(firstName, lastName)
    }

}