package com.example.fetchapptest.domain.fetchrepository

import com.example.fetchapptest.models.FetchItem
import com.example.fetchapptest.presentation.model.FetchItemsListDTO
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
    fun fetchHiringData(): Flow<NetworkResult<FetchItemsListDTO>> = flow {
        val items: List<FetchItem> = clientApiImplementation.fetchHiringEndpoint()
        emit(NetworkResult.Success(FetchItemsListDTO(fetchItems = items, isLoading = false)))
    }.flowOn(Dispatchers.IO)
}


