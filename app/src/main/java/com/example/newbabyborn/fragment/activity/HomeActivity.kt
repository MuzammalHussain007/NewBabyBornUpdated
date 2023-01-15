package com.example.newbabyborn.fragment.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.newbabyborn.R
import com.example.newbabyborn.databinding.ActivityHomeActivtyBinding
import com.example.newbabyborn.fragment.ClothFragment
import com.example.newbabyborn.fragment.FeedingFragment
import com.example.newbabyborn.fragment.HygieneFragment


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

        binding.feeding.setOnClickListener {
            binding.clothingImage.setBackgroundResource(0)
            binding.hygieneImage.setBackgroundResource(0)
            binding.feedingImage.setBackgroundResource(R.drawable.image_shape)
            val newFragment: Fragment = FeedingFragment()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.masterContainer, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        binding.clothing.setOnClickListener {
            binding.feedingImage.setBackgroundResource(0)
            binding.clothingImage.setBackgroundResource(R.drawable.image_shape)
            binding.hygieneImage.setBackgroundResource(0)
            val newFragment: Fragment = ClothFragment()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.masterContainer, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        binding.hygenie.setOnClickListener {
            binding.hygieneImage.setBackgroundResource(R.drawable.image_shape)
            binding.feedingImage.setBackgroundResource(0)
            binding.clothingImage.setBackgroundResource(0)
            val newFragment: Fragment = HygieneFragment()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.masterContainer, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
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