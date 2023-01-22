package com.example.newbabyborn.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newbabyborn.databinding.CustomHomeLayoutBinding
import com.example.newbabyborn.modal.Item

class BabayItemAdapter(
    private val list: ArrayList<Item>,
    val context: Context,
    val addItem: (Int) -> Unit,
    val shareItem: (Int) -> Unit,
    val location: (Int) -> Unit,
 ) : RecyclerView.Adapter<BabayItemAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CustomHomeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CustomHomeLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         with(holder)
        {
            with(list[position])
            {
                binding.customTitle.text = this.itemName
                binding.customPrice.text ="$"+this.itemPrice
                binding.customQuantity.text =this.quantityPurchasedByuser+" item"

                binding.customEdit.setOnClickListener {
                    addItem(position)
                }
                binding.customShare.setOnClickListener {
                    shareItem(position)
                }
                binding.customLocation.setOnClickListener {
                    location(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}