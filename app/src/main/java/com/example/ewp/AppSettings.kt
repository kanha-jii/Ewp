package com.example.ewp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.ewp.model.UserModel
import com.example.ewp.utils.FirebaseUtil
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AppSettings : AppCompatActivity() {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_settings)
        val submitButton = findViewById<Button>(R.id.bt_user_details_submit)
        val userId = FirebaseAuth.getInstance().currentUser?.uid.toString()
        submitButton.setOnClickListener {
            val usernameEt = findViewById<EditText>(R.id.editTextText).text
            val imageLinkEt= findViewById<EditText>(R.id.editTextText2).text
            val userMap = UserModel(
                imageLinkEt.toString(), usernameEt.toString(),
                FirebaseAuth.getInstance().currentUser?.email.toString(), Timestamp.now(),FirebaseUtil.currentUserId()
            )
            firestore.collection("users").document(userId)
                .set(userMap)
                .addOnSuccessListener {
                    Toast.makeText(this, "your details uploaded successfully", Toast.LENGTH_LONG)
                        .show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
                }
        }
    }
}