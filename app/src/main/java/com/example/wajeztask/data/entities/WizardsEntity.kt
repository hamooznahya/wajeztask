package com.example.wajeztask.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wizards_table")
data class WizardsEntity(
    @PrimaryKey
    val id: String,
    val firstName: String?=null,
    val lastName: String?=null,
    val elixirs: List<ElixirListEntity>
)

data class ElixirListEntity(
    val id: String?=null,
    val name: String?=null
)