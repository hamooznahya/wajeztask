package com.example.wajeztask.presentation.home.elixirs

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wajeztask.domain.model.Elixirs
import com.example.wajeztask.domain.model.Wizards
import com.example.wajeztask.domain.usecase.GetElixirsDetailsUseCase
import com.example.wajeztask.domain.usecase.GetWizardsDetailsUseCase
import com.example.wajeztask.domain.usecase.GetWizardsListUseCase
import com.example.wajeztask.presentation.home.wizardsdetails.WizardDetailsFragment.Companion.ELIXIRS_ID
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
class ElixirsDetailsModel
@Inject
constructor(private val useCase: GetElixirsDetailsUseCase, stateHandle: SavedStateHandle) : ViewModel() {



    private val orderID: String? = stateHandle.get<String>(ELIXIRS_ID)


    private val _listResult = MutableStateFlow<ResponseState<Elixirs>>(ResponseState.Loading)
    val listResult: StateFlow<ResponseState<Elixirs>> = _listResult.asStateFlow()

    fun getWizardsDetails() {
        viewModelScope.launch {
            useCase.execute(orderID.orEmpty()).collectLatest {
                _listResult.emit(it)

            }
        }
    }




}