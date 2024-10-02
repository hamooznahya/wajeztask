package com.example.wajeztask.presentation.home.elixirs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wajeztask.domain.model.Elixirs
import com.example.wajeztask.domain.model.Ingredient
import com.example.wajeztask.domain.model.Inventor
import com.example.wajeztask.utils.ResponseState

@Composable
fun ElixirsDetailScreen (viewModel: ElixirsDetailsModel) {
    val wizardsState by viewModel.listResult.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getWizardsDetails()
    }

    when (wizardsState) {
        is ResponseState.Loading -> {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        }

        is ResponseState.Success -> {
            val wizards = (wizardsState as ResponseState.Success<Elixirs>).item
            WizardDetailsItem(wizards)
        }
        is ResponseState.Failure -> {
            Text(text = " ${(wizardsState as ResponseState.Failure).error.errorMessage}")
        }
    }
}

@Composable
fun WizardDetailsItem(elixirs: Elixirs) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "id: ${elixirs.id}", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Name: ${elixirs.name}", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "effect: ${elixirs.effect}", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "sideEffects: ${elixirs.sideEffects}", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "characteristics: ${elixirs.characteristics}", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "time: ${elixirs.time}", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "difficulty: ${elixirs.difficulty}", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "manufacturer: ${elixirs.manufacturer}", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "ingredients:", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(items=elixirs.ingredients) { ingredients ->
                IngredientsItem(ingredients) {
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "inventors:", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(items=elixirs.inventors) { inventors ->
                InventorsItem(inventors) {
                }
            }
        }
    }
}

@Composable
fun IngredientsItem(elixir: Ingredient, onClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${elixir.name.orEmpty()}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "id: ${elixir.id.orEmpty()}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
@Composable
fun InventorsItem(elixir: Inventor, onClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "first Name: ${elixir.firstName.orEmpty()}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "last Name: ${elixir.lastName.orEmpty()}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "id: ${elixir.id.orEmpty()}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
