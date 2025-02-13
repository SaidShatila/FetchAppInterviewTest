package com.example.fetchapptest.presentation.model

import com.example.fetchapptest.models.FetchItem

data class FetchItemsListDTO(
    val fetchItems: List<FetchItem>,
    val isLoading: Boolean
)

