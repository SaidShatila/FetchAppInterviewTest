package com.example.fetchapptest.network.client

import com.example.fetchapptest.models.FetchItemsList
import com.example.fetchapptest.network.model.BaseResponse
import javax.inject.Inject

class ClientApiImplementation @Inject constructor(
    private val clientApi: ClientApi
) : ClientApi {

    override suspend fun fetchHiringEndpoint(): BaseResponse<FetchItemsList> {
        return clientApi.fetchHiringEndpoint()
    }

}