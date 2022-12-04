package be.g55990.messaging.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import be.g55990.messaging.model.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM UserEntity")
    fun getAll(): LiveData<List<UserEntity>>

    @Insert
    fun insert(vararg user: UserEntity)

    @Update
    fun update(vararg user: UserEntity)

    @Query("SELECT * FROM UserEntity WHERE email=:email")
    fun get(vararg email: String) : UserEntity?
}
