package com.example.wajeztask.presentation.home.wizardsdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wajeztask.domain.model.Wizard
import com.example.wajeztask.domain.usecase.GetWizardsDetailsUseCase
import com.example.wajeztask.presentation.home.homefragment.HomeFragment.Companion.WIZARD_ID
import com.example.wajeztask.presentation.home.homefragment.HomeScreenState
import com.example.wajeztask.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WizardDetailsModel
@Inject constructor(private val useCase: GetWizardsDetailsUseCase,
                    stateHandle: SavedStateHandle
) : ViewModel() {

    private val _events = MutableSharedFlow<DetailsPageEvents>()
    val events = _events.asSharedFlow()

    private val orderID: String? = stateHandle.get<String>(WIZARD_ID)

    private val _uiState = MutableStateFlow(WizardDetailsScreenState())
    val uiState = _uiState.asStateFlow()

    fun getWizardsDetails() {
        viewModelScope.launch {
            useCase.execute(orderID.orEmpty()).collectLatest {
                when (it) {
                    is ResponseState.Failure -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                errorMsg = it.error.errorMessage)
                        }
                    }
                    ResponseState.Loading -> {
                        _uiState.update { state -> state.copy(isLoading = true, errorMsg = null) }
                    }
                    is ResponseState.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                wizard = it.item,
                                errorMsg = null
                            )
                        }
                    }
                }

            }
        }
    }

    init {
        getWizardsDetails()
    }
    fun onAction(intent: DetailsPageEvents) {
        when (intent) {
            is DetailsPageEvents.OpenElixirsDetailPage -> handleOpenOrderDetails(intent)
        }
    }
    private fun handleOpenOrderDetails(intent: DetailsPageEvents.OpenElixirsDetailPage) {
        sendEvent(intent)
    }

    private fun sendEvent(events: DetailsPageEvents) {
        viewModelScope.launch {
            _events.emit(events)
        }
    }

}