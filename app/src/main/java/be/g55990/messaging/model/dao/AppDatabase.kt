package be.g55990.messaging.model.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import be.g55990.messaging.model.entity.KeyEntity
import be.g55990.messaging.model.entity.PairEntity
import be.g55990.messaging.model.entity.UserEntity

@Database(entities = [UserEntity::class, KeyEntity::class, PairEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun keyDao(): KeyDao
    abstract fun pairDao(): PairDao

    companion object{
        @Volatile
        private var instance: AppDatabase?=null

        fun getDb(context: Context): AppDatabase {
            return instance ?: synchronized(this){
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "messenger").build()
                return instance as AppDatabase
            }
        }
    }
}