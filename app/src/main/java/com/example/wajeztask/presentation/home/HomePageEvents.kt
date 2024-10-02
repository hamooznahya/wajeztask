package com.example.wajeztask.presentation.home


sealed interface  HomePageEvents {
    data class OpenWizardDetailPage(val wizardId: String) : HomePageEvents

}