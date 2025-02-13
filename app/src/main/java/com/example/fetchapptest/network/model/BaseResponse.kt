package com.example.fetchapptest.network.model

data class BaseResponse<T>(
    val code: Int,
    val status: String,
    val data: T,
)
