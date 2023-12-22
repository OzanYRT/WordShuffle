package com.bcit.wordgame.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bcit.wordgame.dictionary.Dictionary
import kotlin.random.Random

fun randomLetters(): MutableList<String> {
    val letters = mutableListOf("B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z")
    val vowels = mutableListOf("A", "E", "I", "O", "U")
    val selectedLetters = mutableListOf<String>()

    repeat(16) {
        val randomIndex = Random.nextInt(letters.size)
        selectedLetters.add(letters[randomIndex])
    }
    repeat(9) {
        val randomIndex = Random.nextInt(vowels.size)
        selectedLetters.add(vowels[randomIndex])
    }
    selectedLetters.shuffle()

    return selectedLetters
}


@Composable
fun WordGrid(dictionary: Dictionary) {
    var points = remember { mutableStateOf(0) }
    val gridSize = 5 // 5x5 grid
    val letters = remember { mutableStateOf(randomLetters()) }
    var selectedLetters = remember { mutableStateListOf<Int>(0) }
    var currentWord by remember { mutableStateOf("${letters.value[0]}") }
    var currentIndex by remember { mutableStateOf(0) } // Start from top left corner

    fun updateSelection(newIndex: Int) {
        // Add the letter if it's a new selection
        if (!selectedLetters.contains(newIndex)) {
            selectedLetters.add(newIndex)
            currentIndex = newIndex
            currentWord += letters.value[newIndex]
        } else {
            // Remove the letter if backtracking to the last selected cell
            if (selectedLetters.last() == newIndex && selectedLetters.size > 1) {
                selectedLetters.removeAt(selectedLetters.lastIndex)
                currentWord = selectedLetters.joinToString("") { letters.value[it] }
                currentIndex = selectedLetters.last()
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Word: $currentWord",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = "${points.value}",
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
                if (currentIndex >= gridSize) {
                    updateSelection(currentIndex - gridSize)
                }
            }

            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                DirectionButton("Left") {
                    if (currentIndex % gridSize > 0) {
                        updateSelection(currentIndex - 1)
                    }
                }
                DirectionButton("Right") {
                    if (currentIndex % gridSize < gridSize - 1) {
                        updateSelection(currentIndex + 1)
                    }
                }
            }

            DirectionButton("Down") {
                if (currentIndex < letters.value.size - gridSize) {
                    updateSelection(currentIndex + gridSize)
                }
            }
        }

        // OK and Clear Buttons
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = {
                val validWord = dictionary.checkWord(currentWord)
                if(validWord) {
                    points.value += currentWord.length * 5
                }
                // Check if the word is a valid word
            }) {
                Text("Check Word")
            }
            Button(onClick = {
                selectedLetters.clear()
                selectedLetters.add(0)
                currentWord = "${letters.value[0]}"
                currentIndex = 0
            }) {
                Text("Clear")
            }
            Button(onClick = {
                letters.value = randomLetters()
                selectedLetters.clear()
                selectedLetters.add(0)
                currentWord = "${letters.value[0]}"
                currentIndex = 0
            }) {
                Text("Shuffle")
            }
        }
    }
}