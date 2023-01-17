package com.example.newbabyborn.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newbabyborn.adapter.BabayItemAdapter
import com.example.newbabyborn.databinding.FragmentFeedingBinding
import com.example.newbabyborn.fragment.activity.ItemsDetailActivity
import com.example.newbabyborn.modal.Item
import com.google.firebase.database.*
import java.util.*

class FeedingFragment : Fragment() {
    private var _binding: FragmentFeedingBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference
    private lateinit var itemList: ArrayList<Item>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedingBinding.inflate(inflater, container, false)
        val view = binding.root
        innit()
        return view
    }

    private fun innit() {
        binding.progressbar.visibility = View.VISIBLE
        itemList = ArrayList()
        database = FirebaseDatabase.getInstance().getReference("Item")
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
                            val adapter = BabayItemAdapter(
                                itemList,
                                requireContext(),
                                ::addItem,
                                ::shareItem,
                                ::locationItem
                            )
                            binding.feedingRecyclarView.layoutManager =
                                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                            binding.feedingRecyclarView.adapter = adapter
                        }


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
        database.addChildEventListener(childListener);

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    @SuppressLint("SuspiciousIndentation")
    private fun addItem(position: Int) {
        val intent = Intent(requireActivity(), ItemsDetailActivity::class.java)
        intent.putExtra("data", itemList[position])
        requireActivity().startActivity(intent)
    }

    private fun shareItem(position: Int) {
        if(itemList[position].itemName.toString()!="")
        {
            val sendIntent = Intent(Intent.ACTION_VIEW)
            sendIntent.data = Uri.parse("sms:")
            sendIntent.putExtra("sms_body", itemList[position].itemName.toString());
            startActivity(sendIntent);
        }

    }

    private fun locationItem(position: Int) {

        if(itemList[position].itemLangitude.toFloat()!=null && itemList[position].itemLongitude.toFloat() !=null )
        {
            val strUri =
                "http://maps.google.com/maps?q=loc:" + itemList[position].itemLangitude+ "," + itemList[position].itemLongitude.toString()+ " (" + itemList[position].itemLocation + ")"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity")
            startActivity(intent)

        }

    }
}