package com.example.wajeztask.presentation.home.wizardsdetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.wajeztask.domain.model.ElixirList
import com.example.wajeztask.domain.model.Wizard
import com.example.wajeztask.presentation.home.homefragment.ErrorMessage
import com.example.wajeztask.presentation.home.homefragment.LoadingIndicator
import com.example.wajeztask.presentation.home.homefragment.WizardList
import com.example.wajeztask.utils.ResponseState

@Composable
fun WizardDetailScreen (actions: (DetailsPageEvents) -> Unit, state: WizardDetailsScreenState) {


    if(state.wizard!=null){
        WizardDetailsItem(state.wizard){ elixirsId->
            actions(DetailsPageEvents.OpenElixirsDetailPage(elixirsId))
        }
    }
    if(state.isLoading){
        LoadingIndicator()
    }
    if (state.errorMsg!=null){
        ErrorMessage(state.errorMsg)
    }
}

@Composable
fun WizardDetailsItem(wizard: Wizard, onClick: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "first Name: ${wizard.firstName}", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "last Name: ${wizard.lastName}", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Elixirs:", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(items=wizard.elixirs) { elixirs ->
                ElixirsItem(elixirs) {
                    onClick.invoke(elixirs.id.orEmpty())

                }
            }
        }
    }
}

@Composable
fun ElixirsItem(elixir: ElixirList, onClick: () -> Unit) {
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
