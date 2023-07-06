package com.example.ewp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.ewp.fragments.Account
import com.example.ewp.fragments.Catalogue
import com.example.ewp.fragments.Home
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.firebase.auth.FirebaseAuth

class FrameContainer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        setContentView(R.layout.activity_framecontainer)
//        val tt = findViewById<TextView>(R.id.email_text)
//        tt.text = auth.currentUser?.email.toString()
        replaceFragment(Home())

        val bnm = findViewById<NavigationBarView>(R.id.bottomNavigationView)
        bnm.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> replaceFragment(Home())
                R.id.catalogue -> replaceFragment(Catalogue())
                R.id.profile -> replaceFragment(Account())

                else -> {

                }

            }
            true
        }






    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}