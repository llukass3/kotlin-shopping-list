package com.example.shopping_list

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

data class ShoppingItem(var itemName: String){

    var itemAmount: Int = 1
        private set

    fun setAmount(amount: Int) {
        if (itemAmount > 0)
            itemAmount = amount
    }

    fun decreaseAmount(amount: Int){
        if (itemAmount - amount >= 1)
            itemAmount -= amount
    }

    fun increaseAmount(amount: Int){
        itemAmount += amount
    }

}