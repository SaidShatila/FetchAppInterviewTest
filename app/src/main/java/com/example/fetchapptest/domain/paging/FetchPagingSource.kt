package com.example.fetchapptest.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.fetchapptest.models.FetchItem
import com.example.fetchapptest.models.FetchItemsList

class FetchPagingSource(
    private val fetchItemsList: FetchItemsList
) : PagingSource<Int, FetchItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FetchItem> {
        val page = params.key ?: 1
        val pageSize = params.loadSize
        val fromIndex = (page - 1) * pageSize
        val toIndex = minOf(fromIndex + pageSize, fetchItemsList.fetchItems.size)
        if (fromIndex >= fetchItemsList.fetchItems.size) {
            return LoadResult.Page(
                emptyList(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = null
            )
        }
        val pageItems = fetchItemsList.fetchItems.subList(fromIndex, toIndex)
        return LoadResult.Page(
            data = pageItems,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (toIndex < fetchItemsList.fetchItems.size) page + 1 else null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, FetchItem>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}