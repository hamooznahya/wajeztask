package com.example.wajeztask.domain.model


data class Wizards(
    val id: String?=null,
    val firstName: String?=null,
    val lastName: String?=null,
    val elixirs: List<Elixir>
)

data class Elixir(
    val id: String?=null,
    val name: String?=null
)