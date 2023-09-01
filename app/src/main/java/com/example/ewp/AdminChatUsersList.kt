package com.example.ewp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ewp.model.UserModel
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.example.ewp.adapter.SearchUserRecyclerAdapter

class AdminChatUsersList : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val usersRef = db.collection("users")
    private lateinit var adapter:SearchUserRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_chat_users_list)
        setUpSearchRecyclerView("")
        val userEt = findViewById<EditText>(R.id.seach_username_input)
        userEt.requestFocus()
        val searchInput = findViewById<EditText>(R.id.seach_username_input)
        val searchIcon = findViewById<ImageView>(R.id.search_user_btn)
        searchIcon.setOnClickListener {
            val userSearchString = searchInput.text.toString()
            if(userSearchString.isEmpty() || userSearchString.length < 3) {
                searchInput.error = "Invalid username"
                return@setOnClickListener
            }
            setUpSearchRecyclerView(userSearchString)
        }
    }

    private fun setUpSearchRecyclerView(userSearchString: String) {
        val query = usersRef.whereGreaterThanOrEqualTo("userName",userSearchString)
//        val query = usersRef.orderBy("userName", Query.Direction.ASCENDING)
        val options = FirestoreRecyclerOptions.Builder<UserModel>()
            .setQuery(query, UserModel::class.java)
            .build()
        adapter = SearchUserRecyclerAdapter(options,applicationContext)
        val myRecyclerView =  findViewById<RecyclerView>(R.id.users_recycler)
        myRecyclerView.itemAnimator = null
        myRecyclerView.adapter = adapter
        myRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter.startListening()
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
}