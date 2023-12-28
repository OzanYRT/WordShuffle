package com.bcit.wordgame.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.bcit.wordgame.WordGameViewModel

@Composable
fun GameOver(viewModel: WordGameViewModel, nav: NavController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Points: ${viewModel.points.value}")
        Button(onClick = { viewModel.restartGame(navController = nav) }) {
            Text(text = "Play again")
        }
    }
}