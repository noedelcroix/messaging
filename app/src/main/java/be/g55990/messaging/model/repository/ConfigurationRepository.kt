package be.g55990.messaging.model.repository
import android.app.Application
import android.widget.Toast
import be.g55990.messaging.model.dao.AppDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ConfigurationRepository(application: Application) {
    val fb = FirebaseAuth.getInstance()
    val application: Application=application

    fun signIn(email: String, password: String){
        fb.signInWithEmailAndPassword(email, password).addOnFailureListener {
            Toast.makeText(application.applicationContext, "Wrong logins.", Toast.LENGTH_SHORT).show()
        }
    }

    fun signUp(email: String, password: String){
        fb.createUserWithEmailAndPassword(email, password).addOnFailureListener {
            Toast.makeText(application.applicationContext, "Wrong logins.", Toast.LENGTH_SHORT).show()
        }
    }

    fun signOut(){
        fb.signOut()
    }

    fun getCurrentUser(): FirebaseUser? {
        return fb.currentUser
    }
}