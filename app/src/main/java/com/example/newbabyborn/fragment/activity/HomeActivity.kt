package com.example.newbabyborn.fragment.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.newbabyborn.R
import com.example.newbabyborn.databinding.ActivityHomeActivtyBinding
import com.example.newbabyborn.fragment.FeedingFragment


class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeActivtyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeActivtyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        innit()
        clickListener()
    }

    private fun clickListener() {
        binding.addItem.setOnClickListener {
            startActivity(Intent(this,ItemAddActivity::class.java))
        }
    }

    private fun innit() {
        binding.feedingImage.setBackgroundResource(R.drawable.image_shape)
        val newFragment: Fragment = FeedingFragment()
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.masterContainer, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}