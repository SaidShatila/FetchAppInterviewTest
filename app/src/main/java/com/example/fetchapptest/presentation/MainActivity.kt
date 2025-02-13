package com.example.fetchapptest.presentation

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fetchapptest.models.FetchItem
import com.example.fetchapptest.presentation.model.FetchUIState
import com.example.fetchapptest.ui.theme.FetchAppTestTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val fetchViewModel: FetchViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            SystemBarStyle.light(
                ContextCompat.getColor(this, R.color.white),
                ContextCompat.getColor(this, R.color.white)
            ),
        )
        setContent {
            FetchAppTestTheme(
                darkTheme = false,
                dynamicColor = false
            ) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FetchScreen(
                        fetchViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun FetchScreen(viewModel: FetchViewModel = hiltViewModel()) {
    // Collect the current UI state from the ViewModel.
    val uiState by viewModel.fetchUiScreenState.collectAsState()

    // Switch between states
    when (uiState) {
        is FetchUIState.Loading -> {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                Text(text = "Loading...", style = MaterialTheme.typography.displayMedium)
                Spacer(modifier = Modifier.height(8.dp))
                FetchItemsListContent(uiState.list)
            }
        }

        is FetchUIState.Data -> {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                FetchItemsListContent(uiState.list)
            }
        }

        is FetchUIState.Error -> {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                Text(
                    text = "Error: ${(uiState as FetchUIState.Error).msg}",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.displayMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                FetchItemsListContent(uiState.list)
            }
        }
    }
}

@Composable
fun FetchItemsListContent(fetchItemsList: List<FetchItem>) {
    var previousListId = fetchItemsList.firstOrNull()?.fetchListId
    LazyColumn {
        items(
            items = fetchItemsList,
            key = { it.fetchId + Random.nextInt() }
        ) { item ->
            // Display actual data (show the item name)
            if (item.fetchListId != previousListId) {
                previousListId = item.fetchListId
                HorizontalDivider(color = Color.Gray)
            }
            Text(
                text = item.fetchName ?: "No Name",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                color = Color.Black,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FetchAppTestTheme {
        FetchScreen()
    }
}