package com.example.ewp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val registerEt = findViewById<EditText>(R.id.register_email)
        val registerPass = findViewById<EditText>(R.id.register_password)
        val registerConfirmPass = findViewById<EditText>(R.id.register_confirmpassword)
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        val signUpButton = findViewById<Button>(R.id.button_register)

        signUpButton.setOnClickListener {
            val email = registerEt.text.toString()
            val pass = registerPass.text.toString()
            val confirmPass = registerConfirmPass.text.toString()

            if(TextUtils.isEmpty(email)) {
                Toast.makeText(this,"please enter email",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(pass)) {
                Toast.makeText(this,"please enter pass",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(confirmPass)) {
                Toast.makeText(this,"please enter confirm password",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if(pass != confirmPass) {
                Toast.makeText(this,"pass or confirmpass is not same",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    startActivity(Intent(this,FrameContainer::class.java))
                }
                else {
                    val exception = it.exception.toString()
                    Toast.makeText(this,exception,Toast.LENGTH_LONG).show()
                }
            }
        }
//        val toLogin = findViewById<Button>(R.id.register_to_login)
//        toLogin.setOnClickListener {
//            val intent = Intent(this,LoginActivity::class.java)
//            startActivity(intent)
//        }
    }
}