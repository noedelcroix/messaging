package be.g55990.messaging.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import be.g55990.messaging.model.entity.PairEntity
import be.g55990.messaging.model.repository.MessageRepository
import be.g55990.messaging.model.repository.PairRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MessageViewModal(application : Application):  AndroidViewModel(application) {
    val pairRepository: PairRepository
    val messageRepository: MessageRepository

    init{
        pairRepository= PairRepository(application)
        messageRepository = MessageRepository(application)
    }

    fun addPair(user: PairEntity)=viewModelScope.launch(Dispatchers.IO){
        if(pairRepository.get(user.user)!=null) {
            val updated = PairEntity(pairRepository.get(user.user)!!.user, user.name, user.key)
            pairRepository.update(updated)
        }else{
            pairRepository.insert(user)
        }
    }

    fun addMessage(dest:String, input: String){
        messageRepository.addMessage(dest, input)
    }
}