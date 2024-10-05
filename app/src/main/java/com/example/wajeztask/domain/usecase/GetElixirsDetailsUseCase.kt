package com.example.wajeztask.domain.usecase

import com.example.wajeztask.domain.model.Elixirs
import com.example.wajeztask.domain.repository.WizardsRepository
import com.example.wajeztask.utils.ResponseState

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetElixirsDetailsUseCase @Inject constructor(
    private val userRepository: WizardsRepository
) {

    fun execute(id: String): Flow<ResponseState<Elixirs>> {
        return userRepository.getElixirsDetails(id)
    }
}