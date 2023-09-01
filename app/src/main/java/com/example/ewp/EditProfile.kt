package com.example.ewp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.github.dhaval2404.imagepicker.ImagePicker

class EditProfile : AppCompatActivity() {
    private lateinit var imagePickLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        var selectedImageUri: Uri
        val profilePic = findViewById<ImageView>(R.id.profile_image_select)
                imagePickLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                if(data != null && data.data != null) {
                    selectedImageUri = data.data!!
                }
            }
        }

        profilePic.setOnClickListener {
            ImagePicker.with(this).cropSquare().compress(512).maxResultSize(512,512)
                .createIntent {
                    imagePickLauncher.launch(it)
                    return@createIntent
                }
        }
    }
}