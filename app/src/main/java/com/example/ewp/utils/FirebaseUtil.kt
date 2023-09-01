package com.example.ewp.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

//class FirebaseUtil {
//    companion object {
//        fun currentUserId(): String {
//            return FirebaseAuth.getInstance().currentUser?.uid ?: ""
//        }
//    }
//
//}

fun currentUserId(): String {
    return FirebaseAuth.getInstance().currentUser?.uid ?: ""
}
fun currentUser(): FirebaseUser? {
    return FirebaseAuth.getInstance().currentUser
}
fun getChatRoomReference(chatroomId:String): DocumentReference {
    return FirebaseFirestore.getInstance().collection("chatrooms").document(chatroomId)
}

fun getChatroomMessageReference(chatroomId: String): CollectionReference {
    return getChatRoomReference(chatroomId).collection("chats");
}