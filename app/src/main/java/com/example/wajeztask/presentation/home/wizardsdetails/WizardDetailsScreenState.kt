package com.example.wajeztask.presentation.home.wizardsdetails

import com.example.wajeztask.domain.model.Wizard

data class WizardDetailsScreenState(
   val isLoading :Boolean=true,
   val wizard: Wizard?=null,
   val errorMsg:String?=null
)
