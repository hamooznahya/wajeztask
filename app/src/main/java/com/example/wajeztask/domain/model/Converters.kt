package com.example.wajeztask.domain.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromElixirList(elixirs: List<ElixirList>?): String? {
        return gson.toJson(elixirs)
    }

    @TypeConverter
    fun toElixirList(data: String?): List<ElixirList>? {
        val listType = object : TypeToken<List<ElixirList>>() {}.type
        return gson.fromJson(data, listType)
    }
}
