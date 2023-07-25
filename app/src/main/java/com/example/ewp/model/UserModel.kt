package com.example.ewp.model

import androidx.annotation.Keep
import com.google.firebase.Timestamp

@Keep
data class UserModel(var userProfilePic:String? = "", var userName:String? = "anonymous",
                     var userEmail:String? = "", var createdTimestamp: Timestamp?, var userId:String) {
    constructor(): this("", "","", Timestamp.now(),"")
}
