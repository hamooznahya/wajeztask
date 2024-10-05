package com.example.wajeztask.domain.model

import androidx.room.TypeConverter
import com.example.wajeztask.data.entities.ElixirListEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromElixirList(elixirs: List<ElixirListEntity>?): String? {
        return gson.toJson(elixirs)
    }

    @TypeConverter
    fun toElixirList(data: String?): List<ElixirListEntity>? {
        val listType = object : TypeToken<List<ElixirListEntity>>() {}.type
        return gson.fromJson(data, listType)
    }
}
