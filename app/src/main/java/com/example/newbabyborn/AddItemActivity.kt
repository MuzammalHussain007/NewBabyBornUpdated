package com.example.newbabyborn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
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
    private  var itemType: Int?=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)

        val arraySpinner = arrayOf(
            "Category",
            "Diapering",
            "Feeding",
            "Clothing",
            "Gear",
            "Health & Safety",
            "Sleeping",
            "Bathing"
        )
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item, arraySpinner
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.type.adapter = adapter

        binding.type.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                (view as TextView).setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_baseline_arrow_drop_down_24,
                    0
                );

                when (position) {
                    1 -> {
                        itemType = 1
                    }
                    2 -> {
                        itemType = 2
                    }
                    3 -> {
                        itemType=3
                    }
                    4 -> {
                        itemType=4
                    }
                    5 -> {
                        itemType=5
                    }
                    6 -> {
                        itemType=6
                    }
                    7 -> {
                        itemType=7
                    }
                }


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // another interface callback
            }
        }
        val view = binding.root
        setContentView(view)
        database = FirebaseDatabase.getInstance().getReference("Item")
        binding.addItem.setOnClickListener {
            val itemName = binding.itemName.text.toString()
            val itemPrice = binding.itemPrice.text.toString()
            val itemQuantity = binding.Quantity.text.toString()
            val itemDescription = binding.itemDescription.text.toString()
            val itemImage = binding.image.text.toString()
            val itemLongitude = binding.longitude.text.toString()
            val itemLatitude = binding.latitude.text.toString()
            val itemLocation = binding.Location.text.toString()
            binding.progressbar.visibility = View.VISIBLE
            val key = database.push().key
            val item = Item(key!!,itemName,itemPrice,itemQuantity,itemDescription,itemLocation,itemType.toString(),itemLongitude,itemLatitude,itemImage,false)
            database.child(itemType.toString()).child(key).setValue(item).addOnSuccessListener {
              binding.progressbar.visibility = View.GONE
            }


        }

    }
}