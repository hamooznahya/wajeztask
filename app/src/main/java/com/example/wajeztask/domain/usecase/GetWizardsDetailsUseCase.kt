package com.example.wajeztask.domain.usecase

import com.example.wajeztask.data.entities.WizardsEntity
import com.example.wajeztask.domain.model.Wizard
import com.example.wajeztask.domain.repository.WizardsRepository
import com.example.wajeztask.utils.ResponseState

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWizardsDetailsUseCase @Inject constructor(
    private val userRepository: WizardsRepository
) {

    fun execute(id: String): Flow<ResponseState<Wizard>> {
        return userRepository.getWizardsDetails(id)
    }
}