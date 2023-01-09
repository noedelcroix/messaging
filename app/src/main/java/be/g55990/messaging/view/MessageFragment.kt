package be.g55990.messaging.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.g55990.messaging.R
import be.g55990.messaging.adapters.MessageAdapter
import be.g55990.messaging.viewmodel.MessageViewModal

class MessageFragment : Fragment() {
    lateinit var messageViewModal: MessageViewModal
    private lateinit var messageAdapter: MessageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_message, container, false)
        val userid: String = arguments?.getString("userid") ?: ""
        val input: EditText = view.findViewById(R.id.messageToSendInput)
        val submitBtn: Button = view.findViewById(R.id.sendMessageButton)
        val recyclerView = view.findViewById<RecyclerView>(R.id.messageRecyclerView)
        messageViewModal = ViewModelProvider(requireActivity())[MessageViewModal::class.java]
        messageAdapter = MessageAdapter(userid, messageViewModal.messageRepository)

        messageViewModal.messageRepository.fetchMessages(userid)
        messageViewModal.messageRepository.messages.observe(viewLifecycleOwner){
            messageAdapter.updateData()
            recyclerView.scrollToPosition((messageViewModal.messageRepository.messages.value?.size
                ?: 0) - 1)
        }

        messageViewModal.messageRepository.createMessageListener(userid)

        recyclerView.adapter = messageAdapter
        recyclerView.layoutManager=LinearLayoutManager(context)

        submitBtn.setOnClickListener {
            messageViewModal.addMessage(userid, input.text.toString())
            input.text = null
        }

        return view
    }

    override fun onStop() {
        messageViewModal.messageRepository.currentListener.remove()
        super.onStop()
    }
}