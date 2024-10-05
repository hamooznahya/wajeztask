package com.example.wajeztask.presentation.home.homefragment

import com.example.wajeztask.domain.model.Wizard

data class HomeScreenState(
   val isLoading :Boolean=true,
   val listOfWizard: List<Wizard> = emptyList(),
   val errorMsg:String?=null
)
