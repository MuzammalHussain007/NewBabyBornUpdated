package com.example.newbabyborn.fragment.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.newbabyborn.R
import com.example.newbabyborn.databinding.ActivitySecondItemAddBinding


class SecondItemAddActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySecondItemAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondItemAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        innit()
    }

    private fun innit() {
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
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerGender.adapter = adapter

        binding.spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {




            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // another interface callback
            }
        }

    }
}