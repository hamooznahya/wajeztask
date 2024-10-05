package com.example.wajeztask.presentation.home.wizardsdetails


sealed interface  DetailsPageEvents {
    data class OpenElixirsDetailPage(val elixirsId: String) : DetailsPageEvents

}