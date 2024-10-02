package com.example.wajeztask.presentation.home.homefragment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wajeztask.domain.model.Wizards
import com.example.wajeztask.utils.ResponseState

@Composable
fun WizardListScreen( viewModel: HomeViewModel) {
    val wizardsState by viewModel.listResult.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getWizardsList("", "")
    }

    when (wizardsState) {
        is ResponseState.Loading -> {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        }

        is ResponseState.Success -> {
            val wizards = (wizardsState as ResponseState.Success<List<Wizards>>).item
            LazyColumn {
                items(items=wizards) { wizard ->
                    WizardItem(wizard) {

                    }
                }
            }
        }

        is ResponseState.Failure -> {
         //   Text(text = "Error: ${(wizardsState as ResponseState.Error).message}")
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
            Text(text = wizard.firstName, style = MaterialTheme.typography.bodyLarge)
            Text(text = wizard.lastName, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Age: ${wizard.id}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
