package com.example.fetchapptest.network.client

import com.example.fetchapptest.models.FetchItem
import javax.inject.Inject

class ClientApiImplementation @Inject constructor(
    private val clientApi: ClientApi
) : ClientApi {

    override suspend fun fetchHiringEndpoint(): List<FetchItem> {
        return clientApi.fetchHiringEndpoint()
    }

}