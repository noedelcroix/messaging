package be.g55990.messaging.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.g55990.messaging.R
import be.g55990.messaging.adapters.MessageListAdapter
import be.g55990.messaging.viewmodel.MessageViewModal

class MessageListFragment : Fragment() {
    private lateinit var messageViewModal: MessageViewModal
    private lateinit var messageListAdapter: MessageListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView : View = inflater.inflate(R.layout.fragment_message_list, container, false)
        val recyclerView: RecyclerView = rootView.findViewById(R.id.messageListRecyclerView)
        val addPairButton: Button = rootView.findViewById(R.id.peerButton)

        messageViewModal = ViewModelProvider(requireActivity())[MessageViewModal::class.java]
        messageListAdapter = MessageListAdapter(messageViewModal.pairRepository)
        recyclerView.adapter = messageListAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        messageViewModal.pairRepository.pairs.observe(viewLifecycleOwner){
            messageListAdapter.updateData()
        }

        addPairButton.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_message_list_to_fragment_add_peer)
        }

        return rootView
    }
}