package com.example.fetchapptest.domain.fetchrepository

import com.example.fetchapptest.Utils.toNetworkError
import com.example.fetchapptest.network.client.ClientApi
import com.example.fetchapptest.network.model.NetworkResult
import com.example.fetchapptest.presentation.model.FetchItemsListDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchRepository @Inject constructor(
    private val clientApiImplementation: ClientApi
) {
    fun fetchHiringData(): Flow<NetworkResult<FetchItemsListDTO>> = flow {
        emit(NetworkResult.Loading())
        runCatching { clientApiImplementation.fetchHiringEndpoint() }
            .map { FetchItemsListDTO(fetchItems = it, isLoading = false) }
            .fold(
                onSuccess = { emit(NetworkResult.Success(it)) },
                onFailure = { emit(it.toNetworkError()) }
            )
    }.flowOn(Dispatchers.IO)
}


