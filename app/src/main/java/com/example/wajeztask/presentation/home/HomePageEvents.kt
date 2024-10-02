package com.example.wajeztask.presentation.home


sealed interface  HomePageEvents {
    data class OpenWizardDetailPage(val orderID: String) : HomePageEvents

}