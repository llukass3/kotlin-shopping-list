package com.example.shopping_list

import androidx.compose.runtime.Composable

data class ShoppingItem(var itemName: String){

    var itemAmount: Int = 1

    @Composable
    fun ShoppingListEntry() {

    }

    fun setItemName(name: String) {
        itemName = name
    }

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