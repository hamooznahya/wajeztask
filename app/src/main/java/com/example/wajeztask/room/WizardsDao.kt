package com.example.wajeztask.room


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wajeztask.domain.model.Wizards
import kotlinx.coroutines.flow.Flow

@Dao
interface WizardsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(users: List<Wizards>)

    @Query("SELECT * FROM wizards_table")
     fun getAllWizards():  Flow<List<Wizards>>

    @Query("DELETE FROM wizards_table")
     fun deleteAll()
}