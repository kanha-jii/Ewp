package com.example.ewp.adapter
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ewp.ChatActivity
import com.example.ewp.R
import com.example.ewp.model.UserModel
import com.example.ewp.utils.currentUserId
import com.example.ewp.utils.loadImage
import com.example.ewp.utils.passUserModelAsIntent
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class SearchUserRecyclerAdapter(
    options: FirestoreRecyclerOptions<UserModel>,
    context:Context
) :
    FirestoreRecyclerAdapter<UserModel, UserViewHolder>(options) {
    var  context:Context
    init {
        this.context = context
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_user_recycler_view, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int, model: UserModel) {
        holder.userEmail.text = model.userEmail
        holder.usernameText.text = model.userName
//        Glide.with(holder.profilePic).load(Uri.parse("https://achishayari.com/wp-content/uploads/2023/05/Whatsapp-DP-1536x1536.webp"))
        holder.profilePic.loadImage("https://achishayari.com/wp-content/uploads/2023/05/Whatsapp-DP-1536x1536.webp")

        if(model.userId == currentUserId()) {
            holder.usernameText.text = model.userName + "    (me)"
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context,ChatActivity::class.java)
            passUserModelAsIntent(intent , model)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
}

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val usernameText: TextView = itemView.findViewById(R.id.user_name_text)
    val profilePic: ImageView = itemView.findViewById(R.id.profile_pic_image_view)
    var userEmail: TextView = itemView.findViewById(R.id.email_text)
}