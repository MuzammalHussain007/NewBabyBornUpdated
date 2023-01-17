package com.example.newbabyborn.fragment.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.newbabyborn.databinding.ActivityAddItemsBinding
import com.example.newbabyborn.modal.Item

class ItemsDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddItemsBinding
    private var quantity: Int = 0
    private var variableQuantity: Int = 0
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
        binding.btnPositive.setOnClickListener {
            quantity += 1
            variableQuantity += quantity
            binding.itemCount.text = variableQuantity.toString()
            quantity = 0

        }
        binding.btnNegative.setOnClickListener {
            quantity -= 1
            variableQuantity -= quantity
            Log.d("str_______",""+variableQuantity)
//            binding.itemCount.text = variableQuantity.toString()
            quantity=0

        }
        binding.addToolbar.btnTxt.text = "Add items"
        try {
            val item = intent.getSerializableExtra("data") as? Item

            Glide.with(this).load(item!!.itemImage).into(binding.mainImage)
            binding.itemName.text = item.itemName.toString()
            binding.itemPrice.text = item.itemPrice.toString()
            binding.itemDescription.text = item.itemDescription.toString()
            binding.itemCount.text = item.itemQuantity.toString()
            binding.itemLocation.text = item.itemLocation.toString()

            variableQuantity = item.itemQuantity.toInt()


        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}