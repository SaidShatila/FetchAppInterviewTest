package com.example.fetchapptest.presentation.model

import com.example.fetchapptest.models.FetchItem

sealed interface FetchUiState {

    data class LoadingHolder(val isLoading: Boolean) :
        FetchUiState

    data class ErrorHolder(val msg: String) : FetchUiState
    data class SuccessHolder(val list: List<FetchItem>) :
        FetchUiState
}