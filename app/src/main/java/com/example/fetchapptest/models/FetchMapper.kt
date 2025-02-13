package com.example.fetchapptest.models

fun FetchItemsList.toFetchItemsSkeleton(): FetchItemsList {
    return FetchItemsList(
        fetchItems = emptyList(),
        isSkeleton = true
    )
}
fun FetchItemsList.groupAndSortItems(): Map<Int, List<FetchItem>> {
    return fetchItems
        .filter { it.fetchName?.isNotBlank() == true }
        .groupBy { it.fetchListId }
        .toSortedMap()
        .mapValues { (_, items) ->
            items.sortedBy { it.fetchName }
        }
}


fun FetchItemsList.filterGroupSortItems(): List<FetchItem> {
    return fetchItems
        .filter { !it.fetchName.isNullOrBlank() } // Filter out blank or null names
        .groupBy { it.fetchListId }                // Group items by listId
        .toSortedMap()                             // Sort groups by listId (ascending)
        .values
        .flatMap { group ->
            // Within each group, sort by id and then by fetchName
            group.sortedWith(compareBy({ it.fetchId }, { it.fetchName }))
        }
}
