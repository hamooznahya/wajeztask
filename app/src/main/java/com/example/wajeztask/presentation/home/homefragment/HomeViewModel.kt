package com.example.wajeztask.presentation.home.homefragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wajeztask.data.network.NetworkLiveData
import com.example.wajeztask.domain.model.Wizard
import com.example.wajeztask.domain.usecase.GetWizardsListUseCase
import com.example.wajeztask.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val useCase: GetWizardsListUseCase,
    application: Application
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<HomePageEvents>()
    val events = _events.asSharedFlow()

    private val networkLiveData: NetworkLiveData = NetworkLiveData(application)


    init {
        getWizardsList("", "")
        observeNetworkState()
    }

    private fun observeNetworkState() {
        viewModelScope.launch {
            networkLiveData.networkState.collect { isConnected ->
                if (isConnected)
                    getWizardsList("", "")
            }
        }
    }

    fun getWizardsList(firstName: String, secondName: String) {
        viewModelScope.launch {
            useCase.execute(firstName, secondName).collectLatest {
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
                                listOfWizard = it.item,
                                errorMsg = null
                            )
                        }
                    }
                }
            }
        }
    }



    fun onAction(intent: HomePageEvents) {
        when (intent) {
            is HomePageEvents.OpenWizardDetailPage -> handleOpenOrderDetails(intent)
        }
    }

    private fun handleOpenOrderDetails(intent: HomePageEvents.OpenWizardDetailPage) {
        sendEvent(intent)
    }

    private fun sendEvent(events: HomePageEvents) {
        viewModelScope.launch {
            _events.emit(events)
        }
    }


}