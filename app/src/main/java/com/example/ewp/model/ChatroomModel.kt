package com.example.ewp.model

import com.google.firebase.Timestamp

data class ChatroomModel(var chatroomId:String? = "", var lastMessageTimestamp: Timestamp? = Timestamp.now())
