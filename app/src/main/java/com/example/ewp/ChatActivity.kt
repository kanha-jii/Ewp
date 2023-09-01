package com.example.ewp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ewp.adapter.ChatRecyclerAdapter
import com.example.ewp.adapter.SearchUserRecyclerAdapter
import com.example.ewp.model.ChatMessageModel
import com.example.ewp.model.ChatroomModel
import com.example.ewp.model.UserModel
import com.example.ewp.utils.currentUser
import com.example.ewp.utils.currentUserId
import com.example.ewp.utils.getChatRoomReference
import com.example.ewp.utils.getChatroomMessageReference
import com.example.ewp.utils.getUserModelFromIntent
import com.example.ewp.utils.loadImage
import com.example.ewp.utils.showToast
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Query

class ChatActivity : AppCompatActivity() {
    private var chatroomModel: ChatroomModel? = ChatroomModel()
    private lateinit var userModel: UserModel
    private lateinit var messageInput:EditText
    private lateinit var adapter:ChatRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        userModel = getUserModelFromIntent(intent)
        setUpChatRecyclerView()
        val chatUserName = findViewById<TextView>(R.id.chat_screen_username)
        val chatDpPic = findViewById<ImageView>(R.id.profile_pic_image_view)
        val backButton = findViewById<ImageView>(R.id.chat_back_btn)
        val sendButton = findViewById<ImageView>(R.id.message_send_btn)
        messageInput = findViewById<EditText>(R.id.chat_message_input)
        if(userModel.userName == null) {
            chatUserName.text = "EWP-Admin"
            chatDpPic.setImageResource(R.drawable.logo_home)

        }
        else {
            chatUserName.text = userModel.userName
        }
        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        getOrCreateChatroomModel()

        sendButton.setOnClickListener {
            val message = messageInput.text.toString().trim()
            if (message.isEmpty()) {
                return@setOnClickListener
            }
            sendMessageToUser(message)


            setUpChatRecyclerView()
        }
    }

    private fun setUpChatRecyclerView() {

        val query = if(currentUser()?.email == "kanhasinghal0101@gmail.com") {
            getChatroomMessageReference(userModel.userId).orderBy(
                "timestamp",
                Query.Direction.DESCENDING
            )
        } else {
            getChatroomMessageReference(currentUserId()).orderBy("timestamp",Query.Direction.DESCENDING)
        }
//        val query = usersRef.orderBy("userName", Query.Direction.ASCENDING)
        val options = FirestoreRecyclerOptions.Builder<ChatMessageModel>()
            .setQuery(query, ChatMessageModel::class.java)
            .build()
        adapter = ChatRecyclerAdapter(options,applicationContext)
        val myRecyclerView =  findViewById<RecyclerView>(R.id.chat_recycler_view)
        myRecyclerView.itemAnimator = null
        myRecyclerView.adapter = adapter
        val manager = LinearLayoutManager(this)
        manager.reverseLayout = true
        myRecyclerView.layoutManager = manager
        adapter.startListening()

        val observer = object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                // This method will be called when new items are inserted into the adapter
                // You can perform any custom actions here, such as scrolling to the newly added item
                super.onItemRangeInserted(positionStart, itemCount)
                myRecyclerView.scrollToPosition(0)
            }
        }
        adapter.registerAdapterDataObserver(observer)
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onResume() {
        super.onResume()
        adapter.startListening()
    }
    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }


    private fun sendMessageToUser(message: String) {
        val messageModel = ChatMessageModel(message, currentUserId(), Timestamp.now())
        if (currentUser()?.email == "kanhasinghal0101@gmail.com") {
            chatroomModel?.lastMessageTimestamp = Timestamp.now()
            chatroomModel?.chatroomId = userModel.userId
            chatroomModel?.let { getChatRoomReference(userModel.userId).set(it) }
        } else {
            chatroomModel?.let { getChatRoomReference(currentUserId()).set(it) }
            chatroomModel?.lastMessageTimestamp = Timestamp.now()
        }
        if(currentUser()?.email == "kanhasinghal0101@gmail.com") {
            getChatroomMessageReference(userModel.userId).add(messageModel).addOnCompleteListener {
                if (it.isSuccessful) {
                    messageInput.setText("")
                } else {
                    showToast(this, it.exception.toString())
                }
            }
        }
        else {
            getChatroomMessageReference(currentUserId()).add(messageModel).addOnCompleteListener {
                if (it.isSuccessful) {
                    messageInput.setText("")
                } else {
                    showToast(this, it.exception.toString())
                }
            }
        }
    }

    private fun getOrCreateChatroomModel() {
        getChatRoomReference(currentUserId()).get().addOnCompleteListener() {
            if (it.isSuccessful) {
                chatroomModel = it.result.toObject(ChatroomModel::class.java)
                if (chatroomModel == null) {
                    val chatroom = ChatroomModel(currentUserId(), Timestamp.now())
                    getChatRoomReference(currentUserId()).set(chatroom)
                }
            }
        }
    }
}