package com.bcit.wordgame

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.sp
import com.bcit.wordgame.ui.theme.WordGameTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordGrid()
        }
    }
}

fun randomNumbers(): MutableList<String> {
    val letters = mutableListOf("B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z")
    val vowels = mutableListOf("A", "E", "I", "O", "U")
    val selectedLetters = mutableListOf<String>()

    repeat(6) {
        val randomIndex = Random.nextInt(letters.size)
        selectedLetters.add(letters[randomIndex])
    }
    repeat(3) {
        val randomIndex = Random.nextInt(vowels.size)
        selectedLetters.add(vowels[randomIndex])
        vowels.removeAt(randomIndex)
    }
    selectedLetters.shuffle()

    return selectedLetters
}


@Composable
fun WordGrid() {
    val gridSize = 3 // 3x3 grid
    val letters = remember { mutableStateOf(randomNumbers()) }
    var selectedLetters = remember { mutableStateListOf<Int>() }
    var currentWord by remember { mutableStateOf("") }
    var currentIndex by remember { mutableStateOf(0) } // Start from top left corner

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Word: $currentWord",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 20.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(gridSize),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(letters.value.size) { index ->
                LetterBox(
                    letter = letters.value[index],
                    isHighlighted = selectedLetters.contains(index) || index == currentIndex
                )
            }
        }

        // Directional Buttons
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            DirectionButton("Up") {
                currentIndex = (currentIndex - gridSize).coerceAtLeast(0)
            }

            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                DirectionButton("Left") {
                    if (currentIndex % gridSize > 0) currentIndex--
                }
                DirectionButton("Right") {
                    if (currentIndex % gridSize < gridSize - 1) currentIndex++
                }
            }

            DirectionButton("Down") {
                currentIndex = (currentIndex + gridSize).coerceAtMost(letters.value.size - 1)
            }
        }

        // OK and Clear Buttons
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = {
                selectedLetters.add(currentIndex)
                currentWord = selectedLetters.joinToString("") { letters.value[it] }
            }) {
                Text("OK")
            }
            Button(onClick = {
                selectedLetters.clear()
                currentWord = ""
            }) {
                Text("Clear")
            }
            Button(onClick = {
                letters.value = randomNumbers()
                selectedLetters.clear()
                currentWord = ""
                currentIndex = 0
            }) {
                Text("Shuffle")
            }
        }
    }
}

@Composable
fun DirectionButton(direction: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(4.dp)
    ) {
        Text(direction)
    }
}

@Composable
fun LetterBox(letter: String, isHighlighted: Boolean) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(2.dp)
            .size(50.dp)
            .background(if (isHighlighted) Color.Gray else Color.LightGray)
    ) {
        Text(text = letter, fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WordGrid()
}