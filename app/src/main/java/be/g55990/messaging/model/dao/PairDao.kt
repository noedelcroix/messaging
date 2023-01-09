package be.g55990.messaging.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import be.g55990.messaging.model.entity.PairEntity

@Dao
interface PairDao {
    @Query("SELECT * FROM PairEntity WHERE user=:user")
    fun getPair(vararg user: String): PairEntity?

    @Query("SELECT * FROM PairEntity")
    fun get(): LiveData<List<PairEntity>>

    @Insert
    fun insert(vararg pair: PairEntity)

    @Update
    fun update(vararg pair: PairEntity)
}