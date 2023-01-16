package com.example.newbabyborn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.newbabyborn.databinding.ActivityAddItemBinding
import com.example.newbabyborn.databinding.ActivityAddItemsBinding
import com.example.newbabyborn.databinding.ActivityHomeActivtyBinding
import com.example.newbabyborn.modal.Item
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddItemActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddItemBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        database = FirebaseDatabase.getInstance().getReference("Item")
        binding.addItem.setOnClickListener {
            val itemName = binding.itemName.text.toString()
            val itemPrice = binding.itemPrice.text.toString()
            val itemQuantity = binding.Quantity.text.toString()
            val itemDescription = binding.itemDescription.text.toString()
            val itemType = binding.type.text.toString()
            val itemLocation = binding.Location.text.toString()
            binding.progressbar.visibility = View.VISIBLE
            val key = database.push().key
            val item = Item(key!!,itemName,itemPrice,itemQuantity,itemDescription,itemLocation,itemType,"","","")
            database.child(key).setValue(item).addOnSuccessListener {
              binding.progressbar.visibility = View.GONE
            }


        }

    }
}