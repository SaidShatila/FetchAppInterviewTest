package com.example.fetchapptest.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fetchapptest.models.FetchItem


@Composable
fun FetchItemsListContent(fetchItems: List<FetchItem>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        state = rememberLazyListState()
    ) {
        itemsIndexed(fetchItems, key = { _, item -> item.fetchId }) { index, item ->
            if (index != 0 && fetchItems[index - 1].fetchListId != item.fetchListId) {
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.Black
                )
            }
            FetchItemUi(item)
        }
    }
}

@Composable
fun FetchItemUi(fetchItem: FetchItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "ListId = ${fetchItem.fetchListId}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .wrapContentSize()
                .padding(vertical = 8.dp),
            color = Color.Black
        )

        Text(
            text = "Name = ${fetchItem.fetchName}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .wrapContentSize()
                .padding(vertical = 8.dp),
            color = Color.Black
        )

        Text(
            text = "Id = ${fetchItem.fetchId}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .wrapContentSize()
                .padding(vertical = 8.dp),
            color = Color.Black
        )
    }
}