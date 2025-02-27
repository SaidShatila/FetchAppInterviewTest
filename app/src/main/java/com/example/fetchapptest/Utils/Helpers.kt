package com.example.fetchapptest.Utils

import com.example.fetchapptest.network.model.NetworkResult
import com.example.fetchapptest.presentation.model.FetchItemsListDTO
import okio.IOException
import retrofit2.HttpException

fun Throwable.toNetworkError(): NetworkResult.Error<FetchItemsListDTO> =
    NetworkResult.Error(
        message = when (this) {
            is HttpException -> "Server error: ${code()} - ${message()}"
            is IOException -> "Network unavailable. Please try again."
            is IllegalStateException -> message ?: "No data received."
            else -> "Unexpected issue: ${localizedMessage ?: "Unknown error"}"
        }
    )