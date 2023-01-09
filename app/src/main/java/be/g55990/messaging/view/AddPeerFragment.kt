package be.g55990.messaging.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import be.g55990.messaging.R
import be.g55990.messaging.model.entity.PairEntity
import be.g55990.messaging.viewmodel.MessageViewModal
import com.google.android.gms.nearby.Nearby
import com.google.android.gms.nearby.messages.Message
import com.google.android.gms.nearby.messages.MessageListener


class AddPeerFragment : Fragment() {
    lateinit var messageListener: MessageListener
    private lateinit var messageViewModal: MessageViewModal
    lateinit var message: Message
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_peer, container, false)
        messageViewModal = ViewModelProvider(requireActivity())[MessageViewModal::class.java]
        message=Message(messageViewModal.messageRepository.config.getCurrentUser()?.uid!!.encodeToByteArray())
        val pairNameInput: EditText = view.findViewById(R.id.readableNameInput)
        val useridInput: EditText = view.findViewById(R.id.useridInput)
        val sendUid: Button = view.findViewById(R.id.sendUid)
        val saveUidPair: Button = view.findViewById(R.id.add_uid_pair)
        val myUID : TextView = view.findViewById(R.id.labelAddPeerTextView)
        messageListener=object: MessageListener(){
            override fun onFound(p0: Message) {
                super.onFound(p0)
                val received = String(p0.content)
                Log.e(null, "test")
                Log.e(null, received)
                useridInput.setText(p0.content.decodeToString())
            }
        }

        myUID.setText("My UID : "+messageViewModal.messageRepository.config.getCurrentUser()?.uid.toString())

        myUID.setOnClickListener {
            val clipboard: ClipboardManager = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(ClipData.newPlainText("uid", messageViewModal.messageRepository.config.getCurrentUser()?.uid.toString()))
            Toast.makeText(view.context, "UID copied !", Toast.LENGTH_SHORT).show()
        }

        saveUidPair.setOnClickListener {
            if(pairNameInput.text.toString().trim().isNotEmpty() && useridInput.text.toString().trim().isNotEmpty()
            ){
               messageViewModal.addPair(PairEntity(useridInput.text.toString(), pairNameInput.text.toString(), ""))
                Toast.makeText(view.context, "Pair added !", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(view.context, "Invalid pair name or uid.", Toast.LENGTH_SHORT).show()
            }
        }

        sendUid.setOnClickListener {
            Nearby.getMessagesClient(requireContext()).publish(message).addOnSuccessListener {
                Toast.makeText(view.context, "UID sent.", Toast.LENGTH_SHORT).show()
            }
        }


        return view
    }

    override fun onStop() {
        Nearby.getMessagesClient(requireContext()).unsubscribe(messageListener)
        Nearby.getMessagesClient(requireContext()).unpublish(message)
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        Nearby.getMessagesClient(requireContext()).subscribe(messageListener)
    }

}