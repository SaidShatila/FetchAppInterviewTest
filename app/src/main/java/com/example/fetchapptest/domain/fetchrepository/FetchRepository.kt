package com.example.fetchapptest.domain.fetchrepository

import com.example.fetchapptest.models.FetchItem
import com.example.fetchapptest.models.FetchItemsList
import com.example.fetchapptest.network.client.ClientApi
import com.example.fetchapptest.network.model.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchRepository @Inject constructor(
    private val clientApiImplementation: ClientApi
) {
    suspend fun fetchHiringData(): Flow<NetworkResult<FetchItemsList>> = flow {
        // Call the API method directly, which now returns List<FetchItem>
        val items: List<FetchItem> = clientApiImplementation.fetchHiringEndpoint()
        // Wrap the list into your FetchItemsList model
        emit(NetworkResult.Success(FetchItemsList(fetchItems = items, isSkeleton = false)))
    }.flowOn(Dispatchers.IO)
}


//    suspend fun paginateFetchData()


