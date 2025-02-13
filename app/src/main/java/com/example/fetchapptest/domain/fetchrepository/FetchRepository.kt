package com.example.fetchapptest.domain.fetchrepository

import com.example.fetchapptest.models.FetchItem
import com.example.fetchapptest.models.FetchItemsList
import com.example.fetchapptest.network.client.ClientApiImplementation
import com.example.fetchapptest.network.model.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchRepository @Inject constructor(
    private val clientApiImplementation: ClientApiImplementation
) : BaseApiResponse() {

    suspend fun fetchHiringData(): Flow<NetworkResult<FetchItemsList>> = flow {
        emit(safeApiCall { clientApiImplementation.fetchHiringEndpoint() })
    }.flowOn(Dispatchers.IO)
}

