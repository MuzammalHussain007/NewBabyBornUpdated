package com.example.newbabyborn.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newbabyborn.databinding.CustomItemLayoutBinding
import com.example.newbabyborn.modal.Item

class AddItemAdapter(
    private val list: ArrayList<Item>,
    val context: Context,
    val addItemToDetail: (Int) -> Unit,
) : RecyclerView.Adapter<AddItemAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CustomItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CustomItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder)
        {
            with(list[position])
            {
                binding.customTitle.text = this.itemName
                binding.customPrice.text ="$"+this.itemPrice
            }
            holder.itemView.setOnClickListener {
                addItemToDetail(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}