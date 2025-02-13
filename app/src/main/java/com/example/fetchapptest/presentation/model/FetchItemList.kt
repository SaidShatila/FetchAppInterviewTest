package com.example.fetchapptest.presentation.model

import androidx.paging.PagingData
import com.example.fetchapptest.models.FetchItem
import kotlin.random.Random

sealed class FetchItemList(val type: FetchOrderListType, val id: Int) {
    enum class FetchOrderListType {
        FETCHITEMS,
        SKELETON
    }

    data class FetchItemsHolder(val fetchItems: PagingData<FetchItem>) :
        FetchItemList(FetchOrderListType.FETCHITEMS, Random.nextInt())

    data class SkeletonHolder(val dummy: Boolean = true) :
        FetchItemList(FetchOrderListType.SKELETON, kotlin.random.Random.nextInt())

}