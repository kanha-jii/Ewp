package com.example.ewp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    var mAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val lButton = findViewById<Button>(R.id.login_button)
        lButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.login_email).text.toString()
            val pass = findViewById<EditText>(R.id.login_password).text.toString()
            if(TextUtils.isEmpty(email)) {
                Toast.makeText(this,"please enter email first",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            else if(TextUtils.isEmpty(pass)) {
                Toast.makeText(this,"please enter password",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            else {
                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if(it.isSuccessful) {
                        if(mAuth.currentUser?.isEmailVerified == true) {
                            startActivity(Intent(this,FrameContainer::class.java))
                        }
                        else {
                            Toast.makeText(this,"Verify your mail first",Toast.LENGTH_LONG).show()
                        }
                    }
                    else {
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

}