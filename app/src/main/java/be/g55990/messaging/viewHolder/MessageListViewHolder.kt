package be.g55990.messaging.viewHolder

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import be.g55990.messaging.R

class MessageListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    lateinit var userid: String
    init{
        itemView.setOnClickListener {
            val args = Bundle()
            args.putString("userid", userid)
            itemView.findNavController().navigate(R.id.fragment_message, args)
        }
    }
}