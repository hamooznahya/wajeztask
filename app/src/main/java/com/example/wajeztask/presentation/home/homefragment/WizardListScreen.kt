package com.example.wajeztask.presentation.home.homefragment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wajeztask.domain.model.Wizards
import com.example.wajeztask.presentation.home.HomePageEvents
import com.example.wajeztask.utils.ResponseState

@Composable
fun WizardListScreen(
    viewModel: HomeViewModel,
    actions: (HomePageEvents) -> Unit,
    state: HomeScreenState,
) {
    val wizardsState by viewModel.listResult.collectAsState()
    val savedItemsState by viewModel.savedItems.collectAsState(initial = emptyList())

    if (state.online) viewModel.getWizardsList("", "") else viewModel.getCacheList()

    when {
        state.online && wizardsState is ResponseState.Loading -> {
            LoadingIndicator()
        }
        state.online && wizardsState is ResponseState.Success -> {
            WizardList((wizardsState as ResponseState.Success<List<Wizards>>).item, actions)
        }
        state.online && wizardsState is ResponseState.Failure -> {
            ErrorMessage((wizardsState as ResponseState.Failure).error.errorMessage)
        }
        else -> {
            WizardList(savedItemsState, actions)
        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(
        contentAlignment = androidx.compose.ui.Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(modifier = Modifier.size(48.dp))
    }
}

@Composable
fun ErrorMessage(message: String) {
    Text(text = message)
}

@Composable
fun WizardList(wizards: List<Wizards>, actions: (HomePageEvents) -> Unit) {
    LazyColumn {
        items(items = wizards) { wizard ->
            WizardItem(wizard) {
                actions(HomePageEvents.OpenWizardDetailPage(wizard.id.orEmpty()))
            }
        }
    }
}

@Composable
fun WizardItem(wizard: Wizards, onClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "first Name: ${wizard.firstName.orEmpty()}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "last Name: ${wizard.lastName.orEmpty()}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "id: ${wizard.id.orEmpty()}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
