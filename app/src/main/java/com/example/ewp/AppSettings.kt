package com.example.ewp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View

import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import com.example.ewp.model.UserModel
import com.example.ewp.utils.currentUserId
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AppSettings : AppCompatActivity() {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var  imageSelect: ImageView
    private lateinit var imagePickLauncher:ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_settings)
//        val submitButton = findViewById<Button>(R.id.bt_user_details_submit)
//        val progressBar = findViewById<ProgressBar>(R.id.profile_progress_bar)
//        imageSelect = findViewById(R.id.profile_image_select)
//        val userId = FirebaseAuth.getInstance().currentUser?.uid.toString()
//        var selectedImageUri: Uri
//        val profilePic = findViewById<ImageView>(R.id.profile_image_select)
////        imageSelect.setOnClickListener {
////            val iGallery = Intent(Intent.ACTION_PICK)
////            iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
////            startActivityForResult(iGallery,1000) // depreciated
////        }
////        progressBar.visibility = View.GONE
//        submitButton.setOnClickListener {
//            progressBar.visibility = View.VISIBLE
//            val usernameEt = findViewById<EditText>(R.id.editTextText).text
//            val imageLinkEt= findViewById<EditText>(R.id.editTextText2).text
//            val userMap = UserModel(
//                imageLinkEt.toString(), usernameEt.toString(),
//                FirebaseAuth.getInstance().currentUser?.email.toString(), Timestamp.now(),currentUserId()
//            )
//            firestore.collection("users").document(userId)
//                .set(userMap)
//                .addOnSuccessListener {
//                    Toast.makeText(this, "your details uploaded successfully", Toast.LENGTH_LONG)
//                        .show()
//                    progressBar.visibility = View.GONE
//                }
//                .addOnFailureListener { e ->
//                    Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
//                    progressBar.visibility = View.GONE
//                }
//        }
//
//        imagePickLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            if(it.resultCode == Activity.RESULT_OK) {
//                val data = it.data
//                if(data != null && data.data != null) {
//                    selectedImageUri = data.data!!
//                }
//            }
//        }
//
//        profilePic.setOnClickListener {
//            ImagePicker.with(this).cropSquare().compress(512).maxResultSize(512,512)
//                .createIntent {
//                    imagePickLauncher.launch(it)
//                    return@createIntent
//                }
//        }

        val editButton = findViewById<Button>(R.id.edit_button)
        editButton.setOnClickListener {
            startActivity(Intent(this,EditProfile::class.java))
        }
    }
}