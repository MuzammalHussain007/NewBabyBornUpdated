package com.example.newbabyborn.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newbabyborn.R
import com.example.newbabyborn.databinding.CustomHomeLayoutBinding
import com.example.newbabyborn.databinding.CustomItemCategoryBinding
import com.example.newbabyborn.modal.CategoryItem
import com.example.newbabyborn.modal.Item

class CategoryItemAdapter(
    private val list: ArrayList<CategoryItem>,
    val context: Context,
    val onclick:(position:Int)->Unit,
    val moveToNext:(position:Int)->Unit,

) : RecyclerView.Adapter<CategoryItemAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CustomItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CustomItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder)
        {

            with(list[position])
            {
                binding.itemImage.setImageDrawable(context.getDrawable(this.itemImage))
                binding.itemName.text = this.itemName
                binding.itemImage.setOnClickListener {
                    onclick(position)
                    moveToNext(position)
                }

             }
            if(list[position].checked)
            {
                binding.feedingImage.setBackgroundResource(R.drawable.image_shape)
            }else
            {
                binding.feedingImage.setBackgroundResource(0)

            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}