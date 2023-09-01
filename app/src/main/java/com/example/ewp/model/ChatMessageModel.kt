package com.example.ewp.model

import com.google.firebase.Timestamp


data class ChatMessageModel(var message:String="", var senderId:String="",
                            var timestamp: Timestamp
) {
    constructor():this("","",Timestamp.now())
}
