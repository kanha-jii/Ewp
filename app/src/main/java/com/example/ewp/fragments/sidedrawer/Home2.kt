package com.example.ewp.fragments.sidedrawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.navigation.fragment.findNavController
import com.example.ewp.R


class Home2(_value: Int) : Fragment() {

    private var value1 = 1

    init {
        value1 = _value
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home2, container, false)
        val web = view.findViewById<WebView>(R.id.web_view2)
        web.settings.javaScriptEnabled = true

        if (value1 == 2)
            web.loadUrl("https://ewebsiteprovider.com/#/home")

        if (value1 == 3)
            web.loadUrl("https://ewebsiteprovider.com/#/about")

        if (value1 == 4) {
            web.loadUrl("https://ewebsiteprovider.com/#/contact")
        }

        return view

    }

}