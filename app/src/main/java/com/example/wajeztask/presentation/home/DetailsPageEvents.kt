package com.example.wajeztask.presentation.home


sealed interface  DetailsPageEvents {
    data class OpenElixirsDetailPage(val elixirsId: String) : DetailsPageEvents

}