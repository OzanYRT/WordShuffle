package com.bcit.wordgame

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.bcit.wordgame.dictionary.Dictionary
import kotlin.random.Random

class WordGameViewModel() : ViewModel() {
    var letters = mutableStateOf(randomLetters())
    var points = mutableStateOf(0)
    var currentWord = mutableStateOf(letters.value[12])
    var currentIndex = mutableStateOf(12) // Start from the middle
    var selectedLetters = mutableStateListOf<Int>(12)
    var shuffleCooldown = mutableStateOf(false)
    var cooldownTime = mutableStateOf(0)
    var possibleWords = mutableStateOf(setOf<String>())
    var foundWords = mutableStateListOf<String>()
    var gameTime = mutableStateOf(300)

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

    fun shuffleLetters() {
        if (!shuffleCooldown.value) {
            letters.value = randomLetters()
            selectedLetters.clear()
            selectedLetters.add(12)
            currentWord.value = "${letters.value[12]}"
            currentIndex.value = 12

            shuffleCooldown.value = true
            cooldownTime.value = 5
        }
    }

    fun checkWord(dictionary: Dictionary) {
        val validWord = dictionary.checkWord(currentWord.value)
        if(validWord) {
            if(!(foundWords.contains(currentWord.value))) {
                points.value += currentWord.value.length * 5
                foundWords.add(currentWord.value)
            }
        } else {
            points.value -= 5
        }
    }

    fun clearWord() {
        selectedLetters.clear()
        selectedLetters.add(12)
        currentWord.value = "${letters.value[12]}"
        currentIndex.value = 12
    }

    fun updateSelection(newIndex: Int) {
        if (!selectedLetters.contains(newIndex)) {
            selectedLetters.add(newIndex)
            currentIndex.value = newIndex
            currentWord.value += letters.value[newIndex]
        } else {
            if (selectedLetters.last() == newIndex && selectedLetters.size > 1) {
                selectedLetters.removeAt(selectedLetters.lastIndex)
                currentWord.value = selectedLetters.joinToString("") { letters.value[it] }
                currentIndex.value = selectedLetters.last()
            }
        }
    }

    fun restartGame(navController: NavController) {
        points.value = 0
        gameTime.value = 30
        letters.value = randomLetters()
        currentWord.value = "${letters.value[12]}"
        currentIndex.value = 12
        selectedLetters.clear()
        selectedLetters.add(12)
        foundWords.clear()
        navController.navigate("start")
    }

}