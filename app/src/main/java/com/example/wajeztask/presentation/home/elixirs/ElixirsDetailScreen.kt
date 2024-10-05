package com.example.wajeztask.presentation.home.elixirs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wajeztask.domain.model.Elixirs
import com.example.wajeztask.domain.model.Ingredient
import com.example.wajeztask.domain.model.Inventor
import com.example.wajeztask.presentation.home.homefragment.ErrorMessage
import com.example.wajeztask.presentation.home.homefragment.LoadingIndicator

@Composable
fun ElixirsDetailScreen(state: ElixirsDetailsScreenState) {
    if (state.elixirs != null) {
        ElixirsDetailsItem(state.elixirs)
    }
    if (state.isLoading) {
        LoadingIndicator()
    }
    if (state.errorMsg != null) {
        ErrorMessage(state.errorMsg)
    }
}

@Composable
fun ElixirsDetailsItem(elixirs: Elixirs) {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        item {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier
                    .padding( vertical = 6.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                shape = RoundedCornerShape(6.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Text(text = "id: ${elixirs.id}", style = MaterialTheme.typography.titleSmall)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Name: ${elixirs.name}",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "effect: ${elixirs.effect}",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "sideEffects: ${elixirs.sideEffects}",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "characteristics: ${elixirs.characteristics}",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "time: ${elixirs.time}",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "difficulty: ${elixirs.difficulty}",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "manufacturer: ${elixirs.manufacturer}",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
        if (elixirs.ingredients.isNotEmpty()) {
            item {
                Text(text = "Ingredients:", style = MaterialTheme.typography.titleMedium)
            }
            items(elixirs.ingredients) { ingredient ->
                IngredientsItem(ingredient) {}
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
        }


        if (elixirs.inventors.isNotEmpty()) {
            item {
                Text(text = "inventors:", style = MaterialTheme.typography.titleMedium)
            }

            items(elixirs.inventors) { inventor ->
                InventorsItem(inventor) {}
            }
        }

    }
}

@Composable
fun IngredientsItem(elixir: Ingredient, onClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 7.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Name: ${elixir.name.orEmpty()}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(text = "id: ${elixir.id.orEmpty()}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun InventorsItem(elixir: Inventor, onClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 7.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "first Name: ${elixir.firstName.orEmpty()}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "last Name: ${elixir.lastName.orEmpty()}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(text = "id: ${elixir.id.orEmpty()}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
