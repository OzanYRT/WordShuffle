package com.bcit.wordgame.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun DirectionGrid(
    currentIndex: Int,
    gridSize: Int,
    lettersSize: Int,
    updateSelection: (Int) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            DirectionButton("↖") {
                if(currentIndex >= gridSize && currentIndex % gridSize != 0) {
                    updateSelection(currentIndex - gridSize - 1)
                }
            }
            DirectionButton("↑") {
                if (currentIndex >= gridSize) {
                    updateSelection(currentIndex - gridSize)
                }
            }
            DirectionButton("↗") {
                if (currentIndex >= gridSize && (currentIndex + 1) % gridSize != 0) {
                    updateSelection(currentIndex - gridSize + 1)
                }
            }
        }

        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            DirectionButton("←") {
                if (currentIndex % gridSize > 0) {
                    updateSelection(currentIndex - 1)
                }
            }
            DirectionButton("→") {
                if (currentIndex % gridSize < gridSize - 1) {
                    updateSelection(currentIndex + 1)
                }
            }
        }

        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            DirectionButton("↙") {
                if(currentIndex < lettersSize - gridSize && currentIndex % gridSize != 0) {
                    updateSelection(currentIndex + gridSize - 1)
                }
            }
            DirectionButton("↓") {
                if (currentIndex < lettersSize - gridSize) {
                    updateSelection(currentIndex + gridSize)
                }
            }
            DirectionButton("↘") {
                if (currentIndex < lettersSize - gridSize && (currentIndex + 1) % gridSize != 0) {
                    updateSelection(currentIndex + gridSize + 1)
                }
            }
        }

    }
}