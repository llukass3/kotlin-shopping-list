package com.example.shopping_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


data class ShoppingItem(var itemName: String) {

    // Make itemAmount observable by Compose
    var itemAmount by mutableIntStateOf(1)
        private set

    fun setAmount(amount: Int) {
        if (amount > 0)
            itemAmount = amount
    }

    fun decreaseAmount(amount: Int) {
        if (itemAmount - amount >= 1)
            itemAmount -= amount
    }

    fun increaseAmount(amount: Int) {
        itemAmount += amount
    }
}


