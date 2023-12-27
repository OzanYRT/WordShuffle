package com.bcit.wordgame.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun ButtonGrid(
    checkWord: () -> Unit,
    clearWord: () -> Unit,
    shuffleLetters: () -> Unit,
    shuffleCooldown: Boolean,
    cooldownTime: Int
) {
    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Button(onClick = checkWord) {
            Text("Check Word")
        }
        Button(onClick = clearWord) {
            Text("Clear")
        }
        Button(
            onClick = shuffleLetters,
            enabled = !shuffleCooldown
        ) {
            Text(if (shuffleCooldown) "Wait $cooldownTime" else "Shuffle")
        }
    }
}
