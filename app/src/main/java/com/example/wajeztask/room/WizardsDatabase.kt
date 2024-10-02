package com.example.wajeztask.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.wajeztask.domain.model.Converters
import com.example.wajeztask.domain.model.Wizards

@Database(entities = [Wizards::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WizardsDatabase : RoomDatabase() {

    abstract fun wizardsDao(): WizardsDao


}
