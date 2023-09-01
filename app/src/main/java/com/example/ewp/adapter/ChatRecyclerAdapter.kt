package com.example.ewp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ewp.R
import com.example.ewp.model.ChatMessageModel
import com.example.ewp.utils.currentUserId
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class ChatRecyclerAdapter(
    options: FirestoreRecyclerOptions<ChatMessageModel>,
    context:Context
) :
    FirestoreRecyclerAdapter<ChatMessageModel, ChatModelViewHolder>(options) {
    var  context:Context
    init {
        this.context = context
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_message_recycler_row, parent, false)
        return ChatModelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatModelViewHolder, position: Int, model: ChatMessageModel) {
        if(model.senderId == currentUserId()) {
            holder.leftChatLayout.visibility = View.GONE
            holder.rightChatLayout.visibility = View.VISIBLE
            holder.rightChatText.text = model.message
        }
        else {
            holder.leftChatLayout.visibility = View.VISIBLE
            holder.rightChatLayout.visibility = View.GONE
            holder.leftChatText.text = model.message
        }
    }
}

class ChatModelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val leftChatLayout: LinearLayout = itemView.findViewById(R.id.left_chat_layout)
    val rightChatLayout = itemView.findViewById<LinearLayout>(R.id.right_chat_layout)
    val leftChatText = itemView.findViewById<TextView>(R.id.left_chat_textview)
    val rightChatText = itemView.findViewById<TextView>(R.id.right_chat_textview)
}