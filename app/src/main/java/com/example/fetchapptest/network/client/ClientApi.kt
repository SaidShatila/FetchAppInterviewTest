package com.example.fetchapptest.network.client


import com.example.fetchapptest.models.FetchItem
import com.example.fetchapptest.network.NetworkConstants
import retrofit2.http.GET

interface ClientApi {

    @GET(NetworkConstants.HIRING_URL)
    suspend fun fetchHiringEndpoint(): List<FetchItem>

}