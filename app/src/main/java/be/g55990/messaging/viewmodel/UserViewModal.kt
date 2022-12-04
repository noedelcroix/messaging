package be.g55990.messaging.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import be.g55990.messaging.model.dao.AppDatabase
import be.g55990.messaging.model.entity.UserEntity
import be.g55990.messaging.model.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModal(application: Application): AndroidViewModel(application) {
    private val users: LiveData<List<UserEntity>>
    val emails: LiveData<List<String>>
    private val repository: UserRepository

    init{
        repository= UserRepository(AppDatabase.getDb(application).userDao())
        users=repository.users
        emails=Transformations.map(users) { newUser -> newUser.map { it.email } }
    }

    fun insert(user: UserEntity)=viewModelScope.launch(Dispatchers.IO){
        if(repository.get(user.email)!=null) {
            val updated = UserEntity(repository.get(user.email)!!.id, user.email, user.date)
            repository.update(updated)
        }else{
            Log.e(null, "Insert")
            repository.insert(user)
        }
    }
}