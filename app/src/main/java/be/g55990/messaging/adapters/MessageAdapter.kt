package be.g55990.messaging.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.g55990.messaging.R
import be.g55990.messaging.model.repository.MessageRepository
import be.g55990.messaging.viewHolder.MessageViewHolder

class MessageAdapter(userid: String, messageRepository: MessageRepository): RecyclerView.Adapter<MessageViewHolder>(){
    private var userid: String
    private var messageRepository: MessageRepository
    init{
       this.userid=userid
        this.messageRepository=messageRepository
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater.inflate(R.layout.messageitem, parent, false)

        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val itemViewGroup = holder.itemView as ViewGroup
        holder.reversed=messageRepository.messages.value?.get(position)?.from.toString()==userid
        val textView: TextView = itemViewGroup.findViewById(R.id.messageitemtextview)
        textView.text = messageRepository.messages.value?.get(position)?.message.toString()
        holder.initialize()
    }

    override fun getItemCount(): Int {
        return messageRepository.messages.value?.size ?: 0
    }

    fun updateData(){
        notifyDataSetChanged()
    }

}