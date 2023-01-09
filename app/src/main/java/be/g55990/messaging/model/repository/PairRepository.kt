package be.g55990.messaging.model.repository

import android.app.Application
import androidx.lifecycle.LiveData
import be.g55990.messaging.model.dao.AppDatabase
import be.g55990.messaging.model.dao.PairDao
import be.g55990.messaging.model.entity.PairEntity

class PairRepository(application: Application) {
    private val pairDao: PairDao = AppDatabase.getDb(application).pairDao()
    val pairs: LiveData<List<PairEntity>> = pairDao.get()

    fun get(user: String): PairEntity? {
        return pairDao.getPair(user)
    }

    fun get(): LiveData<List<PairEntity>>{
        return pairs
    }

    fun insert(user: PairEntity){
        pairDao.insert(user)
    }

    fun update(user: PairEntity){
        pairDao.update(user)
    }
}