package com.example.ewp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.ewp.R


class Home : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view  = inflater.inflate(R.layout.fragment_home, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ImageView>(R.id.web_development_img)?.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            // Replace the current fragment with the new fragment
//            fragmentTransaction.replace(R.id.frame_layout, WebDevelopmentSub())

            // Add the transaction to the back stack (optional)
            fragmentTransaction.addToBackStack(null)

            // Commit the transaction
            fragmentTransaction.commit()
        }

    }

}