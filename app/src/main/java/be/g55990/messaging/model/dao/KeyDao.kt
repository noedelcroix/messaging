package be.g55990.messaging.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import be.g55990.messaging.model.entity.KeyEntity

@Dao
interface KeyDao {
    @Query("SELECT * FROM KeyEntity LIMIT 1")
    fun getKeys(): KeyEntity?

    @Insert
    fun insert(vararg key: KeyEntity)
}