package com.example.shopping_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.snapshots.SnapshotStateList

// Hauptklasse der App, die von ComponentActivity erbt
class ShoppingList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Erstelle eine reaktive Liste für die Einkaufsartikel
            val shoppingListItems = remember { mutableStateListOf<ShoppingItem>() }

            // Hauptlayout der App
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Header() // Kopfzeile der App
                DisplayShoppingList(
                    shoppingList = shoppingListItems, // Übergabe der Artikelliste
                    onAddItem = { itemName ->
                        shoppingListItems.add(ShoppingItem(itemName)) // Artikel hinzufügen
                    }
                )
            }
        }
    }
}

// Kopfzeile der App
@Composable
fun Header() {
    Box(
        contentAlignment = Alignment.Center, // Zentriere den Inhalt
        modifier = Modifier
            .fillMaxWidth() // Fülle die gesamte Breite
            .height(120.dp) // Höhe der Kopfzeile
            .background(Color.hsv(124f, 0.4f, 0.75f)) // Hintergrundfarbe
    ) {
        Image(
            painter = painterResource(R.drawable.logo), // Logo der App
            contentDescription = "Logo", // Beschreibung für Barrierefreiheit
            modifier = Modifier.padding(top = 30.dp) // Abstand nach oben
        )
    }
}

// Farben für die App
val mainGreen = Color.hsv(124f, 0.4f, 0.75f) // Hauptfarbe (Grün)
val secondaryOrange = Color.hsv(9f, 0.68f, 0.94f) // Sekundärfarbe (Orange)

// Darstellung eines einzelnen Einkaufsartikels
@Composable
fun ShoppingListEntry(
    shoppingItem: ShoppingItem, // Artikel, der dargestellt wird
    onRemove: () -> Unit // Callback zum Entfernen des Artikels
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, // Elemente gleichmäßig verteilen
        verticalAlignment = Alignment.CenterVertically, // Vertikale Zentrierung
        modifier = Modifier
            .fillMaxWidth() // Fülle die gesamte Breite
            .padding(bottom = 5.dp) // Abstand nach unten
    ) {
        // Artikelname
        Text(
            shoppingItem.itemName.uppercase(), // Name in Großbuchstaben
            fontSize = 18.sp, // Schriftgröße
            fontFamily = FontFamily.Serif, // Schriftart
            letterSpacing = 0.7.sp, // Buchstabenabstand
            color = Color.DarkGray, // Textfarbe
            modifier = Modifier.padding(start = 20.dp) // Abstand nach links
        )

        // Bereich für Zähler und Löschen-Button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 25.dp) // Abstand nach rechts
        ) {
            ItemCounter(shoppingItem = shoppingItem) // Zähler für die Artikelanzahl

            // Löschen-Button
            IconButton(
                onClick = { onRemove() }, // Entferne den Artikel
                modifier = Modifier.padding(start = 20.dp) // Abstand nach links
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete, // Löschen-Symbol
                    contentDescription = "Delete Item", // Beschreibung
                    tint = secondaryOrange // Farbe des Symbols
                )
            }
        }
    }
}

// Zähler für die Artikelanzahl
@Composable
fun ItemCounter(
    shoppingItem: ShoppingItem, // Artikel, dessen Anzahl geändert wird
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, // Vertikale Zentrierung
        horizontalArrangement = Arrangement.spacedBy(8.dp), // Abstand zwischen Elementen
        modifier = modifier
    ) {
        // Button zum Verringern der Anzahl
        IconButton(onClick = { shoppingItem.decreaseAmount(1) }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack, // Symbol für "Zurück"
                contentDescription = "Decrease", // Beschreibung
                tint = mainGreen // Farbe des Symbols
            )
        }

        // Anzeige der aktuellen Anzahl
        Text(text = shoppingItem.itemAmount.toString())

        // Button zum Erhöhen der Anzahl
        IconButton(onClick = { shoppingItem.increaseAmount(1) }) {
            Icon(
                imageVector = Icons.Filled.ArrowForward, // Symbol für "Weiter"
                contentDescription = "Increase", // Beschreibung
                tint = mainGreen // Farbe des Symbols
            )
        }
    }
}

// Darstellung der gesamten Einkaufsliste
@Composable
fun DisplayShoppingList(
    shoppingList: SnapshotStateList<ShoppingItem>, // Liste der Artikel
    onAddItem: (String) -> Unit // Callback zum Hinzufügen eines Artikels
) {
    var isAdding by remember { mutableStateOf(false) } // Zustand, ob ein Artikel hinzugefügt wird
    var newItemName by remember { mutableStateOf("") } // Name des neuen Artikels

    Column(
        modifier = Modifier.padding(top = 20.dp), // Abstand nach oben
        horizontalAlignment = Alignment.CenterHorizontally // Zentrierung
    ) {
        // Darstellung aller Artikel in der Liste
        shoppingList.forEach { item ->
            ShoppingListEntry(
                shoppingItem = item,
                onRemove = { shoppingList.remove(item) } // Entferne den Artikel
            )
        }

        // Eingabefeld zum Hinzufügen eines neuen Artikels
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
                    value = newItemName, // Aktueller Wert des Eingabefelds
                    onValueChange = { newItemName = it }, // Aktualisiere den Wert
                    placeholder = { Text("Product Name") }, // Platzhaltertext
                    modifier = Modifier.weight(1f) // Fülle den verfügbaren Platz
                )
                Button(
                    onClick = {
                        val trimmed = newItemName.trim() // Entferne Leerzeichen
                        if (trimmed.isNotEmpty()) {
                            onAddItem(trimmed) // Füge den Artikel hinzu
                            newItemName = "" // Leere das Eingabefeld
                            isAdding = false // Beende den Hinzufügen-Modus
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = mainGreen)
                ) {
                    Text("+", fontSize = 25.sp) // Text des Buttons
                }
            }
        } else {
            // Button zum Aktivieren des Hinzufügen-Modus
            Button(
                onClick = { isAdding = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = mainGreen,
                    contentColor = Color.White
                ),
                modifier = Modifier.padding(top = 10.dp)
            ) {
                Text("+", fontSize = 25.sp) // Text des Buttons
            }
        }
    }
}

// Vorschau der App im Design-Editor
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val shoppingListItems = remember { mutableStateListOf<ShoppingItem>() }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Header() // Kopfzeile
        DisplayShoppingList(
            shoppingList = shoppingListItems, // Artikelliste
            onAddItem = { itemName ->
                shoppingListItems.add(ShoppingItem(itemName)) // Artikel hinzufügen
            }
        )
    }
}