package com.example.newbabyborn.modal

import java.io.Serializable

data class CategoryItem(
    val itemName: String = "",
    val itemImage: Int = -1,
    var checked: Boolean = false
) : Serializable

