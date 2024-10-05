package com.example.wajeztask.presentation.home.homefragment


sealed interface  HomePageEvents {
    data class OpenWizardDetailPage(val wizardId: String) : HomePageEvents

}