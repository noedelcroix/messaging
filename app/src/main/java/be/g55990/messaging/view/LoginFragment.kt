package be.g55990.messaging.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import be.g55990.messaging.R
import be.g55990.messaging.R.layout
import be.g55990.messaging.model.entity.UserEntity
import be.g55990.messaging.viewmodel.UserViewModal
import java.time.LocalDateTime


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewModal: UserViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val rootView : View = inflater.inflate(layout.fragment_login, container, false)
        val input : AutoCompleteTextView = rootView.findViewById(R.id.input)
        val button : Button = rootView.findViewById(R.id.button)
        viewModal= ViewModelProvider(requireActivity())[UserViewModal::class.java]

        viewModal.emails.observe(viewLifecycleOwner) {
            context?.let { it1 ->
                input.setAdapter(
                    ArrayAdapter(
                        it1,
                        android.R.layout.simple_dropdown_item_1line,
                        it
                    )
                )
            }
        }

        button.setOnClickListener {

            if("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex().containsMatchIn(input.text.toString())) {
                viewModal.insert(UserEntity(null, input.text.toString(), LocalDateTime.now().toString()))
                Toast.makeText(rootView.context, "Valid email address.", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(rootView.context, "Invalid email address.", Toast.LENGTH_SHORT).show()
            }

            input.setText("")
        }

        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}