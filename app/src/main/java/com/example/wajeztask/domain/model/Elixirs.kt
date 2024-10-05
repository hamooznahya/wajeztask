package com.example.wajeztask.domain.model


data class Elixirs(
    val id: String?=null,
    val name: String?=null,
    val effect: String?=null,
    val sideEffects: String?=null,
    val characteristics: String?=null,
    val time: String?=null,
    val difficulty: String?=null,
    val ingredients: List<Ingredient>,
    val inventors: List<Inventor>,
    val manufacturer: String?=null
)

