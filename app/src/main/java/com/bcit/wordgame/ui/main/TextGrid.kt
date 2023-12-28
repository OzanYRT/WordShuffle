package com.bcit.wordgame.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.bcit.wordgame.WordGameViewModel

@Composable
fun TextGrid(viewModel: WordGameViewModel) {
    Text(
        text = "Word: ${viewModel.currentWord.value}",
        fontSize = 24.sp,
        modifier = Modifier.padding(top = 20.dp)
    )
    Text(
        text = "Points: ${viewModel.points.value}",
        fontSize = 24.sp
    )
    Text(
        text = "Time left: ${viewModel.gameTime.value} seconds",
        fontSize = 24.sp
    )
    Text(
        text = "All possible words in this grid: ${viewModel.possibleWords.value.size - viewModel.foundWords.size}",
        fontSize = 24.sp,
        modifier = Modifier.padding(top = 20.dp)
    )
}