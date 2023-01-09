package be.g55990.messaging.model.repository

import android.app.Application
import androidx.lifecycle.LiveData
import be.g55990.messaging.model.dao.AppDatabase
import be.g55990.messaging.model.dao.UserDao
import be.g55990.messaging.model.entity.UserEntity

class UserRepository(application: Application) {
    private val userDao: UserDao = AppDatabase.getDb(application).userDao()
    val users: LiveData<List<UserEntity>> = userDao.getAll()

    fun insert(user: UserEntity){
        userDao.insert(user)
    }

    fun update(user: UserEntity){
        userDao.update(user)
    }

    fun get(user: String):UserEntity?{
        return userDao.get(user)
    }
}