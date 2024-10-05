package com.example.wajeztask.room


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wajeztask.data.entities.WizardsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WizardsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(users: List<WizardsEntity>)

    @Query("SELECT * FROM wizards_table")
     fun getAllWizards():  Flow<List<WizardsEntity>>

    @Query("DELETE FROM wizards_table")
     fun deleteAll()
}