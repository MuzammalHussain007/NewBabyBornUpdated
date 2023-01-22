package com.example.newbabyborn.fragment.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newbabyborn.R
import com.example.newbabyborn.adapter.AddItemAdapter
import com.example.newbabyborn.adapter.BabayItemAdapter
import com.example.newbabyborn.databinding.ActivityCategoryDetailBinding
import com.example.newbabyborn.databinding.ActivityHomeActivtyBinding
import com.example.newbabyborn.modal.Item
import com.example.newbabyborn.util.AppStorage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class CategoryDetailActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var itemList: ArrayList<Item>
    private lateinit var binding: ActivityCategoryDetailBinding
    private lateinit var adapter: AddItemAdapter
    private var position: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        innit()
    }

    private fun innit() {
        binding.progressbar.visibility = View.GONE
        binding.noItemFound.visibility = View.VISIBLE
        if (intent != null) {
            position = intent.getIntExtra("position", -1)
            position += 1
            if (position==1)
            {
                binding.customToolbarDetail.btnTxt.text = "Diapering"
            }
            else if (position==2)
            {
                binding.customToolbarDetail.btnTxt.text = "Feeding"
            }
            else if (position==3)
            {
                binding.customToolbarDetail.btnTxt.text = "Clothing"
            }
            else if (position==4)
            {
                binding.customToolbarDetail.btnTxt.text = "Gear"
            }
            else if (position==5)
            {
                binding.customToolbarDetail.btnTxt.text = "Health & Safety"
            }
            else if (position==6)
            {
                binding.customToolbarDetail.btnTxt.text = "Sleeping"
            }
            else if (position==7)
            {
                binding.customToolbarDetail.btnTxt.text = "Bathing"
            }
        }
        binding.customToolbarDetail.backbtn.setOnClickListener {
            finish()
        }

        itemList = ArrayList()
        database = FirebaseDatabase.getInstance().getReference("Item")
        val childListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val contact = snapshot.getValue(Item::class.java);
                binding.progressbar.visibility = View.VISIBLE

                if (snapshot.hasChildren()) {

                    binding.progressbar.visibility = View.GONE
                    binding.noItemFound.visibility = View.GONE

                    if (contact != null) {
                        itemList.add(contact)
                    }else
                    {
                        binding.progressbar.visibility = View.GONE
                    }
                    adapter = AddItemAdapter(
                        itemList,
                        this@CategoryDetailActivity,
                        ::addItemToDetail

                    )
                    binding.itemRecyclarView.layoutManager =
                        LinearLayoutManager(
                            this@CategoryDetailActivity,
                            RecyclerView.VERTICAL,
                            false
                        )
                    binding.itemRecyclarView.adapter = adapter

                } else {
                    binding.progressbar.visibility = View.GONE
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
                binding.progressbar.visibility = View.GONE
                binding.noItemFound.visibility = View.VISIBLE
            }

        }
        database.child(position.toString()).addChildEventListener(childListener);

    }

    private fun addItemToDetail(pos: Int) {

    }
}