package com.example.ewp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.view.WindowCompat
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window,false)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val loginButton = findViewById<Button>(R.id.button)
        val registerButton = findViewById<Button>(R.id.button2)
        loginButton.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
//            startActivity(Intent(this,SplashScreen::class.java))
        }
        registerButton.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }
    override fun onStart() {
        super.onStart()
        if(FirebaseAuth.getInstance().currentUser != null) {
            val intent = Intent(this,FrameContainer::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}