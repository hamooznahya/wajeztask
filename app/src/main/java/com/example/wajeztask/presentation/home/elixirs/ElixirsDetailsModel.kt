package com.example.wajeztask.presentation.home.elixirs

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wajeztask.domain.usecase.GetElixirsDetailsUseCase
import com.example.wajeztask.presentation.home.wizardsdetails.WizardDetailsFragment.Companion.ELIXIRS_ID
import com.example.wajeztask.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ElixirsDetailsModel
@Inject
constructor(private val useCase: GetElixirsDetailsUseCase, stateHandle: SavedStateHandle) :
    ViewModel() {

    private val orderID: String? = stateHandle.get<String>(ELIXIRS_ID)

    init {
        getElixirsDetails()
    }

    private val _uiState = MutableStateFlow(ElixirsDetailsScreenState())
    val uiState = _uiState.asStateFlow()

    fun getElixirsDetails() {
        viewModelScope.launch {
            useCase.execute(orderID.orEmpty()).collectLatest {
                when (it) {
                    is ResponseState.Failure -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                errorMsg = it.error.errorMessage
                            )
                        }
                    }

                    ResponseState.Loading -> {
                        _uiState.update { state -> state.copy(isLoading = true, errorMsg = null) }
                    }

                    is ResponseState.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                elixirs = it.item,
                                errorMsg = null
                            )
                        }
                    }
                }

            }
        }
    }
}