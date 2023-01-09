package be.g55990.messaging.viewHolder

import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.g55990.messaging.R

class MessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var reversed: Boolean = false

    fun initialize(){
        val text: TextView = itemView.findViewById(R.id.messageitemtextview)
        if (reversed){
            text.setBackgroundColor(0xFFFF0000.toInt())
            val layoutParams = text.layoutParams as RelativeLayout.LayoutParams
            layoutParams.removeRule(RelativeLayout.ALIGN_PARENT_END)
            text.layoutParams=layoutParams
        }else{
            text.setBackgroundColor(0xFF2196F3.toInt())
            val layoutParams = text.layoutParams as RelativeLayout.LayoutParams
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END)
            text.layoutParams=layoutParams
        }
    }
}