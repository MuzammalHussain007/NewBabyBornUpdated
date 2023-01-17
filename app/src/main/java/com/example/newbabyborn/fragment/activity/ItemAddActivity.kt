package com.example.newbabyborn.fragment.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newbabyborn.R
import com.example.newbabyborn.adapter.AddItemAdapter
import com.example.newbabyborn.databinding.ActivitySecondItemAddBinding
import com.example.newbabyborn.modal.Item
import com.google.firebase.database.*
import java.util.ArrayList


class ItemAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondItemAddBinding
    private lateinit var database: DatabaseReference
    private lateinit var itemList: ArrayList<Item>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondItemAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addToolbar.btnTxt.text = "Add Items"
        binding.addToolbar.backbtn.setOnClickListener {
            finish()
        }
        innit()
    }

    private fun innit() {
        itemList = ArrayList()
        database = FirebaseDatabase.getInstance().getReference("Item")

        val arraySpinner = arrayOf(
            "Category",
            "Feeding",
            "Cloth",
            "Hygiene"
        )
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item, arraySpinner
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGender.adapter = adapter

        binding.spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
                        setFeeding()
                    }
                    2 -> {
                        setClothing()
                    }
                    3 -> {
                        setHighgiene()
                    }
                }


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // another interface callback
            }
        }

    }

    private fun setFeeding() {
        itemList.clear()
        val childListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val contact = snapshot.getValue(Item::class.java);
                if (snapshot.hasChildren()) {
                    Log.d("auiod_______", "snapshot.hasChildren()")

                    if (contact != null) {
                        Log.d("auiod_______", "snapshot.contact()")
                        binding.progressbar.visibility = View.GONE
                        binding.noItemFound.visibility = View.GONE

                        if (contact.itemType.toInt() == 1) {
                            if (itemList != null) {
                                itemList.clear()
                            }
                            itemList.add(contact)
                            val adapter = AddItemAdapter(
                                itemList, this@ItemAddActivity,::addItemToDetail
                            )
                            binding.itemRecyclarView.layoutManager =
                                LinearLayoutManager(
                                    this@ItemAddActivity,
                                    RecyclerView.VERTICAL,
                                    false
                                )
                            binding.itemRecyclarView.adapter = adapter
                        }


                    } else {
                        binding.noItemFound.visibility = View.VISIBLE

                    }
                } else {
                    binding.noItemFound.visibility = View.VISIBLE
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }

        }
        database.addChildEventListener(childListener);
    }

    private fun setClothing() {
        itemList.clear()
        val childListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val contact = snapshot.getValue(Item::class.java);
                if (snapshot.hasChildren()) {
                    Log.d("auiod_______", "snapshot.hasChildren()")

                    if (contact != null) {
                        Log.d("auiod_______", "snapshot.contact()")
                        binding.progressbar.visibility = View.GONE
                        binding.noItemFound.visibility = View.GONE

                        if (contact.itemType.toInt() == 2) {
                            if (itemList != null) {
                                itemList.clear()
                            }
                            itemList.add(contact)
                            val adapter = AddItemAdapter(
                                itemList, this@ItemAddActivity, ::addItemToDetail
                            )
                            binding.itemRecyclarView.layoutManager =
                                LinearLayoutManager(
                                    this@ItemAddActivity,
                                    RecyclerView.VERTICAL,
                                    false
                                )
                            binding.itemRecyclarView.adapter = adapter
                        }


                    } else {
                        binding.noItemFound.visibility = View.VISIBLE

                    }
                } else {
                    binding.noItemFound.visibility = View.VISIBLE
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }

        }
        database.addChildEventListener(childListener);
    }

    private fun setHighgiene() {
        itemList.clear()
        val childListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val contact = snapshot.getValue(Item::class.java);
                if (snapshot.hasChildren()) {
                    Log.d("auiod_______", "snapshot.hasChildren()")

                    if (contact != null) {
                        Log.d("auiod_______", "snapshot.contact()")
                        binding.progressbar.visibility = View.GONE
                        binding.noItemFound.visibility = View.GONE

                        if (contact.itemType.toInt() == 3) {
                            if (itemList != null) {
                                itemList.clear()
                            }
                            itemList.add(contact)
                            val adapter = AddItemAdapter(
                                itemList, this@ItemAddActivity, ::addItemToDetail
                            )
                            binding.itemRecyclarView.layoutManager =
                                LinearLayoutManager(
                                    this@ItemAddActivity,
                                    RecyclerView.VERTICAL,
                                    false
                                )
                            binding.itemRecyclarView.adapter = adapter
                        }


                    } else {
                        binding.noItemFound.visibility = View.VISIBLE

                    }
                } else {
                    binding.noItemFound.visibility = View.VISIBLE
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }

        }
        database.addChildEventListener(childListener);
    }

    private fun addItemToDetail(position : Int){

        val intent = Intent(this , ItemsDetailActivity::class.java)
        intent.putExtra("data",itemList[position])
        startActivity(intent)

    }
}