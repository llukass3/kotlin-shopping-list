package com.example.shopping_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBackIos
import androidx.compose.material.icons.automirrored.sharp.ArrowForwardIos
import androidx.compose.material.icons.sharp.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

var shoppingItems = ArrayList<ShoppingItem>()

class ShoppingList : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            shoppingItems.add(ShoppingItem("Apfel"))
            shoppingItems.add(ShoppingItem("Kartoffeln"))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Header()
                DisplayShoppingList(shoppingItems)
                AddItem()
            }
        }
    }
}

@Composable
fun Header() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
             .fillMaxWidth()
             .height(120.dp)
             .background(Color.hsv(124f, 0.4f, 0.75f))
        ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .padding(top = 30.dp)
        ) }
}

//displays single entry in the shopping list
@Composable
fun ShoppingListEntry(shoppingItem: ShoppingItem) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
    ) {
        // Item Name
        Text(shoppingItem.itemName,
            fontSize = 20.sp,
            color = Color.DarkGray,
            modifier = Modifier
                .padding(start = 20.dp)
        )

        Row (verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 25.dp)) {

            // Arrow to decrease amount
            Icon(
                imageVector = Icons.AutoMirrored.Sharp.ArrowBackIos,
                contentDescription = "Left Pointing Arrow",
                tint = Color.hsv(124f, 0.4f, 0.75f),
            )

            // The current amount of the item
            Text(shoppingItem.itemAmount.toString(),
                fontSize = 20.sp,
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
            )

            // Arrow to increase amount
            Icon(
                imageVector = Icons.AutoMirrored.Sharp.ArrowForwardIos,
                contentDescription = "Right Pointing Arrow",
                tint = Color.hsv(124f, 0.4f, 0.75f),
            )

            // Delete Button to delete the item from the list
            Icon(
                imageVector = Icons.Sharp.DeleteOutline,
                contentDescription = "Filled Shopping Cart",
                tint = Color.Red,
                modifier = Modifier
                .padding(start = 20.dp)
            )
        }
    }
}

//takes a list of items and builds a shopping list
@Composable
fun DisplayShoppingList(shoppingList: List<ShoppingItem>) {
    if(!shoppingList.isEmpty()) {
        Column (
            modifier = Modifier
                .padding(top = 20.dp)
        ){
            for (item in shoppingList) {
                ShoppingListEntry(item)
            }
        }
    }
}

@Composable
fun AddItem() {
    Button(onClick = {
        shoppingItems.add(ShoppingItem("Apfel"))
    },
    colors = ButtonDefaults.buttonColors(
        containerColor = Color.hsv(124f, 0.4f, 0.75f),
        contentColor = Color.White
    )) {
        Text("+")
    }
}

//TODO: implement
fun DeleteItem() {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    shoppingItems.add(ShoppingItem("Apfel"))
    shoppingItems.add(ShoppingItem("Kartoffeln"))
    shoppingItems.add(ShoppingItem("Chips"))
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Header()
        DisplayShoppingList(shoppingItems)
        AddItem()
    }
}