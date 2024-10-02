package com.example.wajeztask.data.dto

import com.google.gson.annotations.SerializedName


data class WizardsResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("elixirs")
    val elixirs: List<ElixirResponseList>
)

data class ElixirResponseList(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)