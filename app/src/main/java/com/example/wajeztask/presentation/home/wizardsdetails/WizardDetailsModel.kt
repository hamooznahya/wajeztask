package com.example.wajeztask.presentation.home.wizardsdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wajeztask.domain.model.Wizards
import com.example.wajeztask.domain.usecase.GetWizardsDetailsUseCase
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
class WizardDetailsModel
@Inject constructor(private val useCase: GetWizardsDetailsUseCase,
                    stateHandle: SavedStateHandle
)
    : ViewModel() {


//    private val _events = MutableSharedFlow<Events>()
//    val events = _events.asSharedFlow()
    private val orderID: String? = stateHandle.get<String>("orderId")


    private val _listResult = MutableStateFlow<ResponseState<Wizards>>(ResponseState.Loading)
    val listResult: StateFlow<ResponseState<Wizards>> = _listResult.asStateFlow()

    fun getWizardsDetails() {
        viewModelScope.launch {
            useCase.execute(orderID.orEmpty()).collectLatest {
                _listResult.emit(it)

            }
        }
    }


//    private fun sendEvent(events: Events) {
//        viewModelScope.launch {
//            _events.emit(events)
//        }
//    }


}