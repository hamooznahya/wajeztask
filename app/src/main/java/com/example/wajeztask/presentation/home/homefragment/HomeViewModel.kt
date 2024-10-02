package com.example.wajeztask.presentation.home.homefragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wajeztask.domain.model.Wizards
import com.example.wajeztask.domain.usecase.GetWizardsListUseCase
import com.example.wajeztask.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(private val useCase: GetWizardsListUseCase)
    : ViewModel() {


    private val _events = MutableSharedFlow<Events>()
    val events = _events.asSharedFlow()


    private val _listResult = MutableStateFlow<ResponseState<List<Wizards>>>(ResponseState.Loading)
    val listResult: StateFlow<ResponseState<List<Wizards>>> = _listResult.asStateFlow()

    fun getWizardsList(firstName: String, secondName: String) {
        viewModelScope.launch {
            useCase.execute(firstName, secondName).collectLatest {
                _listResult.emit(it)

            }
        }
    }


    private fun sendEvent(events: Events) {
        viewModelScope.launch {
            _events.emit(events)
        }
    }


    sealed class Events {
        object OpenHomePage : Events()
    }
}