package com.bcit.wordgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.bcit.wordgame.dictionary.Dictionary
import com.bcit.wordgame.pages.MainContent

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