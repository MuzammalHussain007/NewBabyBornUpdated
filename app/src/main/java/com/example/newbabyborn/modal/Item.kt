package com.example.newbabyborn.modal

import java.io.Serializable

data class Item(
    val itemID: String = "",
    val itemName: String = "",
    val itemPrice: String = "",
    val itemQuantity: String = "",
    val itemDescription: String = "",
    val itemLocation: String = "",
    val itemType: String = "",
    val itemLangitude: String = "",
    val itemLongitude: String = "",
    val itemImage: String = ""
) : Serializable

