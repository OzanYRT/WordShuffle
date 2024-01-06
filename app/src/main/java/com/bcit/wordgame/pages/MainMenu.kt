package com.bcit.wordgame.pages

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun MainMenu(nav: NavController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "WordShuffle!")
            Button(onClick = { nav.navigate("start") }) {
                Text(text = "Start Game")
            }
            Button(onClick = { nav.navigate("leaderboard") }) {
                Text(text = "Leader Board")
            }
            Button(onClick = {
                sharedPreferences.edit().clear().apply()
                nav.navigate("signup")
            }) {
                Text(text = "Logout")
            }
        }
}