package com.bcit.wordgame.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bcit.wordgame.WordGameViewModel
import com.bcit.wordgame.dictionary.Dictionary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
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
fun WordGrid(dictionary: Dictionary, viewModel: WordGameViewModel) {
    val gridSize = 5 // 5x5 grid
    val letters = remember { mutableStateOf(randomLetters()) }
    var selectedLetters = remember { mutableStateListOf<Int>(12) }
    var currentWord by remember { mutableStateOf("${letters.value[12]}") }
    var currentIndex by remember { mutableStateOf(12) } // Start from the middle

    var possibleWords = remember { mutableStateOf(setOf<String>()) }
    var foundWords = remember { mutableStateListOf<String>() }

    var shuffleCooldown by remember { mutableStateOf(false) }
    var cooldownTime by remember { mutableStateOf(0) }

    // Background processing
    LaunchedEffect(letters.value) {
        withContext(Dispatchers.Default) {
            val words = dictionary.graphifyAndFindWords(letters.value, gridSize)
            withContext(Dispatchers.Main) {
                possibleWords.value = words.distinct().toSet()
            }
        }
    }

    fun shuffleLetters() {
        if (!shuffleCooldown) {
            letters.value = randomLetters()
            selectedLetters.clear()
            selectedLetters.add(12)
            currentWord = "${letters.value[12]}"
            currentIndex = 12

            shuffleCooldown = true
            cooldownTime = 5
        }
    }

    fun checkWord() {
        val validWord = dictionary.checkWord(currentWord)
        if(validWord) {
            if(!(foundWords.contains(currentWord))) {
                viewModel.points.value += currentWord.length * 5
                foundWords.add(currentWord)
            }
        } else {
            viewModel.points.value -= 5
        }
    }

    fun clearWord() {
        selectedLetters.clear()
        selectedLetters.add(12)
        currentWord = "${letters.value[12]}"
        currentIndex = 12
    }

    LaunchedEffect(shuffleCooldown) {
        while (shuffleCooldown && cooldownTime > 0) {
            delay(1000)
            cooldownTime--
        }
        shuffleCooldown = false
    }


    fun updateSelection(newIndex: Int) {
        if (!selectedLetters.contains(newIndex)) {
            selectedLetters.add(newIndex)
            currentIndex = newIndex
            currentWord += letters.value[newIndex]
        } else {
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
            text = "Points: ${viewModel.points.value}",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = "All possible words in this grid: ${possibleWords.value.size - foundWords.size}",
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

        DirectionGrid(
            currentIndex = currentIndex,
            gridSize = gridSize,
            lettersSize = letters.value.size,
            updateSelection = ::updateSelection
        )

        ButtonGrid(
            checkWord = {checkWord()},
            clearWord = {clearWord()},
            shuffleLetters = { shuffleLetters() },
            shuffleCooldown = shuffleCooldown,
            cooldownTime = cooldownTime
        )
    }
}