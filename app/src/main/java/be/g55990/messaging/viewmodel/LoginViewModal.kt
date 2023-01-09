package be.g55990.messaging.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import be.g55990.messaging.model.repository.ConfigurationRepository

class LoginViewModal(application: Application): AndroidViewModel(application) {
    private val config : ConfigurationRepository
    val logged : MutableLiveData<Boolean>

    init{
        config = ConfigurationRepository(application)
        logged = MutableLiveData<Boolean>()
        config.fb.addAuthStateListener {
            logged.value = it.currentUser!=null
        }
    }

    fun login(email:String, password: String){
        config.signIn(email, password)
    }

    fun signup(email:String, password: String){
        config.signUp(email, password)
    }

    fun signOut(){
        config.signOut()
    }
}