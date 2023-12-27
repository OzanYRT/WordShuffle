package com.bcit.wordgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.bcit.wordgame.dictionary.Dictionary
import com.bcit.wordgame.ui.main.DirectionButton
import com.bcit.wordgame.ui.main.LetterBox
import com.bcit.wordgame.ui.main.MainContent
import com.bcit.wordgame.ui.main.WordGrid
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val dictionary = Dictionary(this)
            val viewModel = ViewModelProvider(this)[WordGameViewModel::class.java]

            MainContent(dictionary, viewModel)
        }
    }
}