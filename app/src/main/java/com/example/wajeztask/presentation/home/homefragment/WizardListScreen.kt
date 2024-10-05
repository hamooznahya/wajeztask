package com.example.wajeztask.presentation.home.homefragment

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wajeztask.domain.model.Wizard

@Composable
fun WizardListScreen(
    actions: (HomePageEvents) -> Unit,
    state: HomeScreenState,
) {


    if(state.listOfWizard.isNotEmpty()){
        WizardList(state.listOfWizard,actions)
    }
    if(state.isLoading){
        LoadingIndicator()
    }
    if (state.errorMsg!=null){
        ErrorMessage(state.errorMsg)
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
    val context = LocalContext.current
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
fun WizardList(wizards: List<Wizard>, actions: (HomePageEvents) -> Unit) {
    LazyColumn {
        items(items = wizards) { wizard ->
            WizardItem(wizard) {
                actions(HomePageEvents.OpenWizardDetailPage(wizard.id.orEmpty()))
            }
        }
    }
}

@Composable
fun WizardItem(wizard: Wizard, onClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "first Name: ${wizard.firstName.orEmpty()}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "last Name: ${wizard.lastName.orEmpty()}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "id: ${wizard.id}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
