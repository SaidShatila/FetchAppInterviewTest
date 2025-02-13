package com.example.fetchapptest.network.client

import com.example.fetchapptest.models.FetchItemsList
import com.example.fetchapptest.network.NetworkConstants
import com.example.fetchapptest.network.model.BaseResponse
import retrofit2.http.GET

interface ClientApi {

    @GET(NetworkConstants.HIRING_URL)
    suspend fun fetchHiringEndpoint(): BaseResponse<FetchItemsList>
}