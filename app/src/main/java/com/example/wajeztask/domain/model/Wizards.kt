package com.example.wajeztask.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wizards_table")
data class Wizards(
    @PrimaryKey
    val id: String,
    val firstName: String?=null,
    val lastName: String?=null,
    val elixirs: List<ElixirList>
)

data class ElixirList(
    val id: String?=null,
    val name: String?=null
)