package com.example.ewp.utils

import  android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


fun ImageView.loadImage(usl: String){
    val glide = Glide.with(context)
    glide.load(usl).apply(RequestOptions.circleCropTransform()).into(this)
}