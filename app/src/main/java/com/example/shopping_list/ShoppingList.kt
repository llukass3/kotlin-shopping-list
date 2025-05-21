package com.example.shopping_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBackIos
import androidx.compose.material.icons.automirrored.sharp.ArrowForwardIos
import androidx.compose.material.icons.sharp.DeleteOutline
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ShoppingList : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val shoppingListItems = remember {
                mutableStateListOf<ShoppingItem>()
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Header()
                DisplayShoppingList(
                    shoppingList = shoppingListItems,
                    onAddItem = { itemName ->
                        shoppingListItems.add(ShoppingItem(itemName))
                    }
                )
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
            modifier = Modifier.padding(top = 30.dp)
        )
    }
}

val mainGreen = Color.hsv(124f, 0.4f, 0.75f)
val secondaryOrange = Color.hsv(9f, 0.68f, 0.94f)

@Composable
fun ShoppingListEntry(shoppingItem: ShoppingItem) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    ) {
        Text(
            shoppingItem.itemName.uppercase(),
            fontSize = 18.sp,
            fontFamily = FontFamily.Serif,
            letterSpacing = 0.7.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(start = 20.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 25.dp)
        ) {
            ItemCounter(shoppingItem = shoppingItem)

            Icon(
                imageVector = Icons.Sharp.DeleteOutline,
                contentDescription = "Delete Item",
                tint = secondaryOrange,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
}

@Composable
fun ItemCounter(
    shoppingItem: ShoppingItem,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        IconButton(onClick = {
            shoppingItem.decreaseAmount(1)
        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Sharp.ArrowBackIos,
                contentDescription = "Decrease",
                tint = mainGreen
            )
        }

        Text(text = shoppingItem.itemAmount.toString())

        IconButton(onClick = {
            shoppingItem.increaseAmount(1)
        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Sharp.ArrowForwardIos,
                contentDescription = "Increase",
                tint = mainGreen
            )
        }
    }
}

@Composable
fun DisplayShoppingList(
    shoppingList: MutableList<ShoppingItem>,
    onAddItem: (String) -> Unit
) {
    var isAdding by remember { mutableStateOf(false) }
    var newItemName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (shoppingList.isNotEmpty()) {
            for (item in shoppingList) {
                ShoppingListEntry(item)
            }
        }

        if (isAdding) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                TextField(
                    value = newItemName,
                    onValueChange = { newItemName = it },
                    placeholder = { Text("Produktname") },
                    modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = {
                        val trimmed = newItemName.trim()
                        if (trimmed.isNotEmpty()) {
                            onAddItem(trimmed)
                            newItemName = ""
                            isAdding = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = mainGreen)
                ) {
                    Text("+", fontSize = 25.sp)
                }
            }
        } else {
            Button(
                onClick = { isAdding = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = mainGreen,
                    contentColor = Color.White
                ),
                modifier = Modifier.padding(top = 10.dp)
            ) {
                Text("+", fontSize = 25.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val shoppingListItems = remember {
        mutableStateListOf<ShoppingItem>()
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Header()
        DisplayShoppingList(
            shoppingList = shoppingListItems,
            onAddItem = { itemName ->
                shoppingListItems.add(ShoppingItem(itemName))
            }
        )
    }
}
