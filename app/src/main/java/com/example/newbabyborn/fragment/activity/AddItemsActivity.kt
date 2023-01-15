package com.example.newbabyborn.fragment.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newbabyborn.R
import com.example.newbabyborn.databinding.ActivityAddItemsBinding
import com.example.newbabyborn.databinding.ActivitySignUpBinding

class AddItemsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddItemsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        innit()
    }

    private fun innit() {

    }
}