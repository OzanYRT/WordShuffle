package com.bcit.wordgame.pages

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.bcit.wordgame.WordGameViewModel
import com.bcit.wordgame.ui.main.UsersState

@Composable
fun GameOver(viewModel: WordGameViewModel, nav: NavController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "High Score: ${sharedPreferences.getInt("highScore", 0)}")
        Text(text = "Current Score: ${viewModel.points.value}")
        Button(onClick = { viewModel.restartGame(navController = nav) }) {
            Text(text = "Play again")
        }
        Button(onClick = { nav.navigate("menu") }) {
            Text(text = "Go to menu")
        }
    }
}