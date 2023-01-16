package com.example.newbabyborn.fragment.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.newbabyborn.R
import com.example.newbabyborn.databinding.ActivityAddItemsBinding
import com.example.newbabyborn.databinding.ActivitySignUpBinding
import com.example.newbabyborn.modal.Item

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
        binding.addToolbar.backbtn.setOnClickListener {
            finish()
        }
        binding.addToolbar.btnTxt.text = "Item"
        try {
            val item = intent.getSerializableExtra("data") as? Item

            Glide.with(this).load(item!!.itemImage).into(binding.mainImage)
            binding.itemName.text = item.itemName.toString()
            binding.itemPrice.text = item.itemPrice.toString()
            binding.itemDescription.text = item.itemDescription.toString()
            binding.itemCount.text = item.itemQuantity.toString()
            binding.itemLocation.text = item.itemLocation.toString()


        }catch (e : Exception)
        {
            e.printStackTrace()
        }

    }
}