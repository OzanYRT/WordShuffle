package com.bcit.wordgame

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class WordGameViewModel : ViewModel() {
    var points = mutableStateOf(0)
    var currentWord = mutableStateOf("")
    var currentIndex = mutableStateOf(0) // Start from the middle
    var letters = mutableStateOf(randomLetters())
    var selectedLetters = mutableStateListOf<Int>()
    var shuffleCooldown = mutableStateOf(false)
    var cooldownTime = mutableStateOf(0)
    var possibleWords = mutableStateOf(setOf<String>())
    var foundWords = mutableStateListOf<String>()

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

}