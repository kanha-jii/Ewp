package com.example.ewp.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.ewp.model.UserModel

public fun showToast(context: Context, message:String) {
    Toast.makeText(context,message,Toast.LENGTH_LONG).show()
}

fun passUserModelAsIntent(intent: Intent,model: UserModel) {
    intent.putExtra("userName",model.userName)
    intent.putExtra("userId",model.userId)
    intent.putExtra("userDp",model.userProfilePic)
}