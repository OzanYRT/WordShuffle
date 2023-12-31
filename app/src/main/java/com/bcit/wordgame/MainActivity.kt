package com.bcit.wordgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelProvider
import com.bcit.wordgame.dictionary.Dictionary
import com.bcit.wordgame.pages.MainContent
import com.bcit.wordgame.ui.main.UsersState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        val name = sharedPreferences.getString("name", "")
        val email = sharedPreferences.getString("email", "")
        val highScore = sharedPreferences.getInt("highscore", 0)

        val usersRepository = (application as MyApp).usersRepository

        setContent {

            val dictionary = Dictionary(this)
            val viewModel = ViewModelProvider(this)[WordGameViewModel::class.java]

            val usersState = remember {
                UsersState(usersRepository)
            }

            MainContent(usersState, dictionary, viewModel, isLoggedIn, highScore)
        }
    }
}