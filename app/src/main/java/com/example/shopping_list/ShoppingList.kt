package com.example.shopping_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBackIos
import androidx.compose.material.icons.automirrored.sharp.ArrowForwardIos
import androidx.compose.material.icons.sharp.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

var shoppingItems = ArrayList<ShoppingItem>()

class ShoppingList : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Header()
        }
    }
}

@Composable
fun Header() {
    Text("My Shopping List")
}

//displays single entry in the shopping list
@Composable
fun ShoppingListEntry(shoppingItem: ShoppingItem) {
    Row {
        // Item Name
        Text(shoppingItem.itemName)

        // Arrow to decrease amount
        Icon(
            imageVector = Icons.AutoMirrored.Sharp.ArrowBackIos,
            contentDescription = "Left Pointing Arrow"
        )

        // The current amount of the item
        Text(shoppingItem.itemAmount.toString())

        // Arrow to increase amount
        Icon(
            imageVector = Icons.AutoMirrored.Sharp.ArrowForwardIos,
            contentDescription = "Right Pointing Arrow"
        )

        // Delete Button to delete the item from the list
        Icon(
            imageVector = Icons.Sharp.DeleteOutline,
            contentDescription = "Filled Shopping Cart",
            tint = Color.Red
        )
    }
}

//takes a list of items and builds a shopping list
@Composable
fun DisplayShoppingList(shoppingList: ArrayList<ShoppingItem>) {
    if(!shoppingList.isEmpty()) {
        Column {
            for (item in shoppingList) {
                ShoppingListEntry(item)
            }
        }
    }
}

@Composable
fun AddItem() {

}

fun DeleteItem() {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    shoppingItems.add(ShoppingItem("Apfel"))
    shoppingItems.add(ShoppingItem("Kartoffeln"))
    DisplayShoppingList(shoppingItems)
}