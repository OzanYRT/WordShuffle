package com.bcit.wordgame.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bcit.wordgame.WordGameViewModel
import com.bcit.wordgame.dictionary.Dictionary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun WordGrid(dictionary: Dictionary, viewModel: WordGameViewModel, nav: NavController) {
    val gridSize = 5 // 5x5 grid

    // Background processing for finding all the possible words
    // in the current grid
    LaunchedEffect(viewModel.letters.value) {
        withContext(Dispatchers.Default) {
            val words = dictionary.graphifyAndFindWords(viewModel.letters.value, gridSize)
            withContext(Dispatchers.Main) {
                viewModel.possibleWords.value = words.distinct().toSet()
            }
        }
    }

    // Shuffle cooldown handler
    LaunchedEffect(viewModel.shuffleCooldown.value) {
        while (viewModel.shuffleCooldown.value && viewModel.cooldownTime.value > 0) {
            delay(1000)
            viewModel.cooldownTime.value--
        }
        viewModel.shuffleCooldown.value = false
    }

    LaunchedEffect(viewModel.gameTime.value) {
        while(viewModel.gameTime.value > 0) {
            delay(1000)
            viewModel.gameTime.value--
        }
        nav.navigate("gameOver")
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        TextGrid(viewModel)

        LetterGrid(viewModel = viewModel, gridSize = gridSize)

        DirectionGrid(
            currentIndex = viewModel.currentIndex.value,
            gridSize = gridSize,
            lettersSize = viewModel.letters.value.size,
            updateSelection = { newIndex -> viewModel.updateSelection(newIndex) }
        )

        ButtonGrid(
            checkWord = { viewModel.checkWord(dictionary) },
            clearWord = { viewModel.clearWord() },
            shuffleLetters = { viewModel.shuffleLetters() },
            shuffleCooldown = viewModel.shuffleCooldown.value,
            cooldownTime = viewModel.cooldownTime.value
        )
    }
}