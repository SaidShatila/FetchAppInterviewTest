package com.example.fetchapptest.presentation

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fetchapptest.presentation.model.FetchUiState
import com.example.fetchapptest.presentation.ui.ErrorUi
import com.example.fetchapptest.presentation.ui.FetchItemsListContent
import com.example.fetchapptest.presentation.ui.LoadingUi
import com.example.fetchapptest.ui.theme.FetchAppTestTheme
import dagger.hilt.android.AndroidEntryPoint

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
    val uiState by viewModel.fetchUiScreenState.collectAsState()
    when (uiState) {
        is FetchUiState.ErrorHolder -> {
            ErrorUi((uiState as FetchUiState.ErrorHolder).msg)
        }

        is FetchUiState.LoadingHolder -> {
            LoadingUi()
        }

        is FetchUiState.SuccessHolder -> {
            FetchItemsListContent((uiState as FetchUiState.SuccessHolder).list)
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