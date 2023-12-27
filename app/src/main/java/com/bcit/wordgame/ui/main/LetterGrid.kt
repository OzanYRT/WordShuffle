package com.bcit.wordgame.ui.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.bcit.wordgame.WordGameViewModel

@Composable
fun LetterGrid(viewModel: WordGameViewModel, gridSize: Int) {
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
}