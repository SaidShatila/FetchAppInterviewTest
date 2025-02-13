package com.example.fetchapptest.presentation.model

import com.example.fetchapptest.models.FetchItem
import com.example.fetchapptest.models.FetchItemsList


sealed interface FetchUIState {
    val list: List<FetchItem>

    data class Loading(
        override val list: List<FetchItem>,
        val isPaginating: Boolean
    ) : FetchUIState

    data class Data(
        override val list: List<FetchItem>,
        val canPaginate: Boolean
    ) : FetchUIState

    data class Error(
        override val list: List<FetchItem>,
        val msg: String,
        val isPaginating: Boolean
    ) : FetchUIState
}
