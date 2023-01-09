package be.g55990.messaging.model.repository
import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import be.g55990.messaging.model.entity.Message
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MessageRepository(application: Application) {
    private val db: FirebaseFirestore = Firebase.firestore
    val config: ConfigurationRepository = ConfigurationRepository(application)
    val messages: MutableLiveData<List<Message>> = MutableLiveData()
    lateinit var currentListener: ListenerRegistration

    fun fetchMessages(dest : String) {
        db.collection("messages").whereEqualTo("from", config.getCurrentUser()?.uid.toString()).whereEqualTo("to", dest).get().addOnSuccessListener { result1 ->
            db.collection("messages").whereEqualTo("from", dest)
                .whereEqualTo("to", config.getCurrentUser()?.uid.toString()).get().addOnSuccessListener {
                    result2->
                messages.value = (result1.toObjects(Message::class.java)+result2.toObjects(Message::class.java)).sortedBy { it.date }
                    Log.e(null, messages.value.toString())
            }
        }
    }

    fun createMessageListener(userid: String){
        currentListener = db.collection("messages").addSnapshotListener { value, _ ->
            Log.e(null, value.toString())
            fetchMessages(userid)
        }
    }

    fun addMessage(dest: String, message: String){
        val data = hashMapOf(
            "from" to config.getCurrentUser()?.uid.toString(),
            "to" to dest,
            "message" to message,
            "date" to LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME).toString()
        )

        db.collection("messages").add(data)
    }
}