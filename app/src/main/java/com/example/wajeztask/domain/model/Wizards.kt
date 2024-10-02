package com.example.wajeztask.domain.model


data class Wizards(
    val id: String?=null,
    val firstName: String?=null,
    val lastName: String?=null,
    val elixirs: List<ElixirList>
)

data class ElixirList(
    val id: String?=null,
    val name: String?=null
)