package com.example.shopping_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class ShoppingList : ComponentActivity() {

    var shoppingItems = ArrayList<ShoppingItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Logo()
        }
    }
}

@Composable
fun Logo(){
    Text("My Shopping List")
}

@Composable
fun ShoppingListContainer() {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Logo()
}