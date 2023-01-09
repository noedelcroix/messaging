package be.g55990.messaging.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import be.g55990.messaging.R
import be.g55990.messaging.R.layout
import be.g55990.messaging.model.entity.UserEntity
import be.g55990.messaging.viewmodel.LoginViewModal
import be.g55990.messaging.viewmodel.UserViewModal
import java.time.LocalDateTime

class LoginFragment : Fragment() {
    private lateinit var userViewModal: UserViewModal
    private lateinit var loginViewModal: LoginViewModal

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val rootView : View = inflater.inflate(layout.fragment_login, container, false)
        val emailInput : AutoCompleteTextView = rootView.findViewById(R.id.emailInput)
        val passwordInput : EditText = rootView.findViewById(R.id.passwordInput)
        val loginButton : Button = rootView.findViewById(R.id.connexionButton)
        val signupButton : Button = rootView.findViewById(R.id.inscriptionButton)

        userViewModal= ViewModelProvider(requireActivity())[UserViewModal::class.java]
        loginViewModal= ViewModelProvider(requireActivity())[LoginViewModal::class.java]

        userViewModal.emails.observe(viewLifecycleOwner) {
            context?.let { it1 ->
                emailInput.setAdapter(
                    ArrayAdapter(
                        it1,
                        android.R.layout.simple_dropdown_item_1line,
                        it
                    )
                )
            }
        }

        loginButton.setOnClickListener {

            if("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex().containsMatchIn(emailInput.text.toString())) {
                userViewModal.insert(UserEntity(null, emailInput.text.toString(), LocalDateTime.now().toString()))
                loginViewModal.login(emailInput.text.toString(), passwordInput.text.toString())
            }else{
                Toast.makeText(rootView.context, "Invalid email address.", Toast.LENGTH_SHORT).show()
            }
        }

        signupButton.setOnClickListener {

            if("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex().containsMatchIn(emailInput.text.toString())) {
                userViewModal.insert(UserEntity(null, emailInput.text.toString(), LocalDateTime.now().toString()))
                loginViewModal.signup(emailInput.text.toString(), passwordInput.text.toString())
            }else{
                Toast.makeText(rootView.context, "Invalid email address.", Toast.LENGTH_SHORT).show()
            }
        }

        return rootView
    }
}