package com.bcit.wordgame.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bcit.wordgame.WordGameViewModel
import com.bcit.wordgame.dictionary.Dictionary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun WordGrid(dictionary: Dictionary, viewModel: WordGameViewModel) {
    val gridSize = 5 // 5x5 grid

    // Background processing
    LaunchedEffect(viewModel.letters.value) {
        withContext(Dispatchers.Default) {
            val words = dictionary.graphifyAndFindWords(viewModel.letters.value, gridSize)
            withContext(Dispatchers.Main) {
                viewModel.possibleWords.value = words.distinct().toSet()
            }
        }
    }

    LaunchedEffect(viewModel.shuffleCooldown.value) {
        while (viewModel.shuffleCooldown.value && viewModel.cooldownTime.value > 0) {
            delay(1000)
            viewModel.cooldownTime.value--
        }
        viewModel.shuffleCooldown.value = false
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Word: ${viewModel.currentWord.value}",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = "Points: ${viewModel.points.value}",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = "All possible words in this grid: ${viewModel.possibleWords.value.size - viewModel.foundWords.size}",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 20.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(gridSize),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(viewModel.letters.value.size) { index ->
                LetterBox(
                    letter = viewModel.letters.value[index],
                    isHighlighted = viewModel.selectedLetters.contains(index) || index == viewModel.currentIndex.value
                )
            }
        }

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