package be.g55990.messaging.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.g55990.messaging.R
import be.g55990.messaging.model.repository.PairRepository
import be.g55990.messaging.viewHolder.MessageListViewHolder

class MessageListAdapter(val pairRepository: PairRepository): RecyclerView.Adapter<MessageListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater.inflate(R.layout.messagelistitem, parent, false)

        return MessageListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pairRepository.get().value?.size ?: 0
    }

    override fun onBindViewHolder(holder: MessageListViewHolder, position: Int) {
        val itemViewGroup = holder.itemView as ViewGroup
        val textView: TextView = itemViewGroup.findViewById(R.id.Email)
        textView.text =pairRepository.get().value?.get(position)?.name ?: ""
        holder.userid=pairRepository.get().value?.get(position)?.user ?: ""
    }

    fun updateData(){
        notifyDataSetChanged()
    }
}