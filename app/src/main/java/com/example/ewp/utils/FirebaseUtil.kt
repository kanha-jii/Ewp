package com.example.ewp.utils

import com.google.firebase.auth.FirebaseAuth

class FirebaseUtil {
    companion object {
        fun currentUserId(): String {
            return FirebaseAuth.getInstance().currentUser?.uid ?: ""
        }
    }

}