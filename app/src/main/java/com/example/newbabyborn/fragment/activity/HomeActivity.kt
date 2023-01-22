package com.example.newbabyborn.fragment.activity

import SwipeToDeleteCallback
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Color.green
import android.graphics.Path.Direction
import com.example.newbabyborn.R
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newbabyborn.adapter.BabayItemAdapter
import com.example.newbabyborn.adapter.CategoryItemAdapter
import com.example.newbabyborn.databinding.ActivityHomeActivtyBinding
import com.example.newbabyborn.modal.CategoryItem
import com.example.newbabyborn.modal.Item
import com.example.newbabyborn.util.AppStorage
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeActivtyBinding
    private lateinit var database: DatabaseReference
    private lateinit var itemList: ArrayList<Item>
    private var previousSelectedLanguage = -1
    private lateinit var cateGoryitemList: ArrayList<CategoryItem>
    private lateinit var adapter: BabayItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeActivtyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        AppStorage.init(this)
        innit()
        innitCategory()
        clickListener()
    }

    private fun innitCategory() {
        cateGoryitemList.add(CategoryItem("Diapering", R.drawable.diapering))
        cateGoryitemList.add(CategoryItem("Feeding", R.drawable.feeding))
        cateGoryitemList.add(CategoryItem("Clothing", R.drawable.clothing))
        cateGoryitemList.add(CategoryItem("Gear", R.drawable.other))
        cateGoryitemList.add(CategoryItem("Health & Safety", R.drawable.health))
        cateGoryitemList.add(CategoryItem("Sleeping", R.drawable.sleeping))
        cateGoryitemList.add(CategoryItem("Bathing", R.drawable.bathing))
        val cateGoryAdapter = CategoryItemAdapter(cateGoryitemList, this, ::moveToActivity) {

        }
        binding.categoryRecyclarView.layoutManager =
            LinearLayoutManager(this@HomeActivity, RecyclerView.HORIZONTAL, false)
        binding.categoryRecyclarView.adapter = cateGoryAdapter


    }

    private fun clickListener() {
        binding.homeToolbar.btnLogout.setOnClickListener {
            AppStorage.init(this)
            AppStorage.setUser(false)
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        binding.addItem.setOnClickListener {
            startActivity(Intent(this, ItemAddActivity::class.java))
        }
    }

    private fun innit() {
        cateGoryitemList = ArrayList()
        previousSelectedLanguage = AppStorage.getItemPosition()!!
//        if (previousSelectedLanguage >= 0) {
//            Log.d("mansoor", "index is in if:$previousSelectedLanguage")
//            cateGoryitemList[previousSelectedLanguage].checked = true
//        } else {
//            cateGoryitemList[0].checked = true
//
//        }

        itemList = ArrayList()
        database = FirebaseDatabase.getInstance().getReference("yourProduct")
        val childListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val contact = snapshot.getValue(Item::class.java);
                binding.progressbar.visibility = View.VISIBLE

                if (snapshot.hasChildren()) {

                    Log.d("auiod_______", "snapshot.hasChildren()")

                    if (contact != null) {
                        Log.d("auiod_______", "snapshot.contact()")
                        binding.progressbar.visibility = View.GONE
                        binding.noItemFound.visibility = View.GONE

                        itemList.add(contact)
                        adapter = BabayItemAdapter(
                            itemList,
                            this@HomeActivity,
                            ::addItem,
                            ::shareItem,
                            ::locationItem
                        )
                        binding.itemRecyclarView.layoutManager =
                            LinearLayoutManager(this@HomeActivity, RecyclerView.VERTICAL, false)
                        binding.itemRecyclarView.adapter = adapter

                    } else {
                        binding.progressbar.visibility = View.GONE
                        binding.noItemFound.visibility = View.VISIBLE

                    }
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
            }

        }
        database.child(FirebaseAuth.getInstance().currentUser!!.uid)
            .addChildEventListener(childListener);
        enableLeftRightSwipe()


    }

    @SuppressLint("SuspiciousIndentation")
    private fun addItem(position: Int) {
        val intent = Intent(this, ItemsDetailActivity::class.java)
        intent.putExtra("data", itemList[position])
        intent.putExtra("inputType", 0)
        startActivity(intent)
    }

    private fun shareItem(position: Int) {
        if (itemList[position].itemName.toString() != "") {
            val sendIntent = Intent(Intent.ACTION_VIEW)
            sendIntent.data = Uri.parse("sms:")
            sendIntent.putExtra("sms_body", itemList[position].itemName.toString());
            startActivity(sendIntent);
        }

    }

    private fun locationItem(position: Int) {

        if (itemList[position].itemLongitude.toFloat() != null && itemList[position].itemLongitude.toFloat() != null) {
            val strUri =
                "http://maps.google.com/maps?q=loc:" + itemList[position].itemLongitude + "," + itemList[position].itemLongitude.toString() + " (" + itemList[position].itemLocation + ")"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
            intent.setClassName(
                "com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"
            )
            startActivity(intent)

        }

    }

    private fun enableLeftRightSwipe() {

        val callback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                try {
                    val position = viewHolder.adapterPosition
                    if (direction == ItemTouchHelper.LEFT) {
                        database.child(FirebaseAuth.getInstance().currentUser!!.uid)
                            .child(itemList[position].itemID).removeValue()
                        itemList.removeAt(position)
                        adapter.notifyItemRemoved(position)

                        if (itemList.size == 0) {
                            binding.noItemFound.visibility = View.VISIBLE
                        }
                    } else {
                        val item = Item(
                            itemList[position].itemID,
                            itemList[position].itemName,
                            itemList[position].itemPrice,
                            itemList[position].itemQuantity,
                            itemList[position].itemDescription,
                            itemList[position].itemLocation,
                            itemList[position].itemType,
                            itemList[position].itemLatitude,
                            itemList[position].itemLongitude,
                            itemList[position].itemImage,
                            true,
                            itemList[position].quantityPurchasedByuser
                        )
                        database.child(FirebaseAuth.getInstance().currentUser!!.uid)
                            .child(itemList[position].itemID)
                            .setValue(item).addOnSuccessListener {
                                Toast.makeText(
                                    applicationContext,
                                    "Item Purchased",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        adapter.notifyDataSetChanged()
                    }

                } catch (e: Exception) {
                    Log.e("MainActivity", e.message!!)
                }
            }

            // You must use @RecyclerViewSwipeDecorator inside the onChildDraw method
            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                RecyclerViewSwipeDecorator.Builder(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                    .addSwipeLeftBackgroundColor(
                        ContextCompat.getColor(
                            this@HomeActivity,
                            R.color.red
                        )
                    )
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_add_shopping_cart_24)
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                    .addSwipeRightBackgroundColor(
                        ContextCompat.getColor(
                            this@HomeActivity,
                            R.color.green
                        )
                    )
                    .addSwipeRightLabel("Purchase")
                    .setSwipeRightLabelColor(Color.WHITE)
                    .addSwipeLeftLabel("Delete")
                    .setSwipeLeftLabelColor(Color.WHITE) //.addCornerRadius(TypedValue.COMPLEX_UNIT_DIP, 16)
                    //.addPadding(TypedValue.COMPLEX_UNIT_DIP, 8, 16, 8)
                    .create()
                    .decorate()
                super.onChildDraw(
                    c, recyclerView!!,
                    viewHolder!!, dX, dY, actionState, isCurrentlyActive
                )
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.itemRecyclarView)


    }

    private fun moveToActivity(position: Int) {
        var intent = Intent(this, CategoryDetailActivity::class.java)
        intent.putExtra("position", position)
        startActivity(intent)

    }


}