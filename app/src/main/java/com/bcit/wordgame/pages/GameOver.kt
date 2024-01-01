package com.bcit.wordgame.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.bcit.wordgame.WordGameViewModel

@Composable
fun GameOver(viewModel: WordGameViewModel, nav: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Points: ${viewModel.points.value}")
        Button(onClick = { viewModel.restartGame(navController = nav) }) {
            Text(text = "Play again")
        }
    }
}