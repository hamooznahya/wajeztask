package com.example.wajeztask.data.dto

import com.google.gson.annotations.SerializedName

data class ElixirsResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("effect")
    val effect: String,
    @SerializedName("sideEffects")
    val sideEffects: String,
    @SerializedName("characteristics")
    val characteristics: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("difficulty")
    val difficulty: String,
    @SerializedName("ingredients")
    val ingredients: List<IngredientResponse>,
    @SerializedName("inventors")
    val inventors: List<InventorResponse>,
    @SerializedName("manufacturer")
    val manufacturer: String
)

data class IngredientResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)

data class InventorResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String
)