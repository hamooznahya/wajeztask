package com.example.wajeztask.presentation.home.elixirs

import com.example.wajeztask.domain.model.Elixirs

data class ElixirsDetailsScreenState(
   val isLoading :Boolean=true,
   val elixirs: Elixirs?=null,
   val errorMsg:String?=null
)
