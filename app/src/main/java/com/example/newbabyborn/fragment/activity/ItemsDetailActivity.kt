package com.example.newbabyborn.fragment.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.newbabyborn.databinding.ActivityAddItemsBinding
import com.example.newbabyborn.modal.Item
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class ItemsDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddItemsBinding
    private var quantity: Int = 0
    private var newQuantity: Int = 0
    private var item: Item? = null
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        innit()
    }

    private fun innit() {
        try {
            item = intent.getSerializableExtra("data") as? Item
            Glide.with(this).load(item!!.itemImage).into(binding.mainImage)
            binding.addImagebtn.visibility = View.GONE
            binding.itemImage.visibility = View.GONE
            binding.image.visibility = View.GONE
            binding.itemName.text = item!!.itemName.toString()
            binding.itemPrice.text = item!!.itemPrice.toString()
            binding.itemDescription.text = item!!.itemDescription.toString()
            binding.itemCount.text = "0"
            binding.itemLocation.text = item!!.itemLocation.toString()

            val type = intent.getIntExtra("inputType", -1)

            if (type == 1) {
                binding.addToolbar.btnTxt.text = "Add items"
                binding.editItem.visibility = View.GONE
                binding.addItem.visibility = View.VISIBLE
            } else if (type == 0) {
                binding.addToolbar.btnTxt.text = "Edit items"
                binding.editItem.visibility = View.VISIBLE
                binding.addItem.visibility = View.GONE
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        database = FirebaseDatabase.getInstance().getReference("yourProduct")


        binding.addToolbar.backbtn.setOnClickListener {
            finish()
        }
        binding.pinLocation.setOnClickListener {
            Toast.makeText(applicationContext, "Your Location Pinned", Toast.LENGTH_SHORT).show()
        }
        binding.addItem.setOnClickListener {
            binding.progressbar.visibility = View.VISIBLE
            val key = database.push().key
            val user = FirebaseAuth.getInstance().currentUser!!.uid
            newQuantity = quantity


            val item = Item(
                key!!,
                item!!.itemName,
                item!!.itemPrice,
                item!!.itemQuantity,
                item!!.itemDescription,
                item!!.itemLocation,
                item!!.itemType,
                item!!.itemLatitude,
                item!!.itemLongitude,
                item!!.itemImage,
                false,
                newQuantity.toString()
            )
            database.child(user.toString()).child(key).setValue(item).addOnSuccessListener {
                binding.progressbar.visibility = View.GONE
                Toast.makeText(applicationContext, "Item Added", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        binding.editItem.setOnClickListener {
            newQuantity = quantity
            binding.progressbar.visibility = View.VISIBLE
            val item = Item(
                item!!.itemID,
                item!!.itemName,
                item!!.itemPrice,
                item!!.itemQuantity,
                item!!.itemDescription,
                item!!.itemLocation,
                item!!.itemType,
                item!!.itemLatitude,
                item!!.itemLongitude,
                item!!.itemImage,
                false,
                newQuantity.toString()
            )
            database.child(FirebaseAuth.getInstance().currentUser!!.uid).child(item!!.itemID)
                .setValue(item).addOnSuccessListener {
                binding.progressbar.visibility = View.GONE
                Toast.makeText(applicationContext, "Item Updated", Toast.LENGTH_SHORT).show()
                finish()

            }
        }

        binding.btnPositive.setOnClickListener {
            quantity = binding.itemCount.text.toString().toInt()
            quantity++
            binding.itemCount.text = quantity.toString()


        }
        binding.btnNegative.setOnClickListener {
            quantity = binding.itemCount.text.toString().toInt()
            if (quantity > 0) {
                quantity--
                binding.itemCount.text = quantity.toString()
            }
        }


    }
}