package com.example.wajeztask.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.wajeztask.domain.model.Converters
import com.example.wajeztask.data.entities.WizardsEntity

@Database(entities = [WizardsEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WizardsDatabase : RoomDatabase() {

    abstract fun wizardsDao(): WizardsDao


}
