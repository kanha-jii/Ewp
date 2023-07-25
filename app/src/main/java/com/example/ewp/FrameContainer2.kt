package com.example.ewp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.ewp.fragments.Home
import com.example.ewp.fragments.sidedrawer.Home2
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class FrameContainer2 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame_container2)
        navigationView = findViewById(R.id.nav_view)
        drawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.isFocusableInTouchMode = false
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        navigationView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.frame_container, Home2(2))
                .commit()
            navigationView.setCheckedItem(R.id.home2)
        }

        toggle.syncState()

        val callback = onBackPressedDispatcher.addCallback(this, false) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {

            override fun onDrawerOpened(drawerView: View) {
                callback.isEnabled = true
            }

            override fun onDrawerClosed(drawerView: View) {
                callback.isEnabled = false
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) = Unit
            override fun onDrawerStateChanged(newState: Int) = Unit
        })

//        val callback: Any = object : OnBackPressedCallBack(true) {
//            override fun handleOnBackPressed() {
//                //ToDo Implement your custom event here
//            }
//        }
//
//        this@FrameContainer2.onBackPressedDispatcher.addCallback(this@FrameContainer2, callback)
//        val onBackPressedCallback = object: OnBackPressedCallback(true) {
//
//            override fun handleOnBackPressed() {
//
//                // Your business logic to handle the back pressed event
//                Toast.makeText(baseContext,"hiiii",Toast.LENGTH_LONG).show()
//
//            }
//            override fun
//
//        }
//        this.onBackPressedDispatcher
//            .addCallback(onBackPressedCallback)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home2 -> {
                replaceFragment(Home2(2))
                navigationView.setCheckedItem(R.id.home2)
            }

            R.id.services -> {
                replaceFragment(Home())
                navigationView.setCheckedItem(R.id.services)
            }

            R.id.about_us -> {
                replaceFragment(Home2(3))
                navigationView.setCheckedItem(R.id.about_us)
            }

            R.id.send_feedback -> {
                replaceFragment(Home2(4))
                navigationView.setCheckedItem(R.id.send_feedback)
            }

            R.id.com_whatsapp -> {
//                if(FirebaseAuth.getInstance().currentUser.email.toString() == "kanhasinghal0101@gmail.com") {
                    val intent = Intent(this, AdminChatUsersList::class.java)
                    startActivity(intent)
            }
            R.id.settings -> {
                startActivity(Intent(this,AppSettings::class.java))
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return false
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_container, fragment)
        fragmentTransaction.commit()
    }

//    override fun onBackPressed() {
//        Toast.makeText(this, "backpressesd", Toast.LENGTH_LONG).show()
//        Log.e("ONBACKPRESSED", "call ho gaya -------------------------------")
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START)
//        }
//        else {
//            super.getOnBackPressedDispatcher()
//        }
//    }
//    override fun onBackPressed() {
//        Toast.makeText(this,"hello my custom onBackPressed is called",Toast.LENGTH_LONG).show()
//    }

//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        if(keyCode==KeyEvent.KEYCODE_BACK) {
//            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//                drawerLayout.closeDrawer(GravityCompat.START)
//            } else {
//                super.getOnBackPressedDispatcher()
//            }
//        }
//        return true
//    }

}
