package com.example.fetchapptest.models

import com.google.gson.annotations.SerializedName

data class FetchItem(
    @SerializedName("id")
    val fetchId: Int,
    @SerializedName("listId")
    val fetchListId: Int,
    @SerializedName("name")
    val fetchName: String?
)

