package com.example.fetchapptest.models

import com.example.fetchapptest.presentation.model.FetchItemsListDTO

fun FetchItemsListDTO.filterGroupSortItems(): List<FetchItem> {
    return fetchItems
        .filter { !it.fetchName.isNullOrBlank() }
        .groupBy { it.fetchListId }
        .toSortedMap()
        .values
        .flatMap { group -> group.sortedWith(compareBy({ it.fetchId }, { it.fetchName })) }
}