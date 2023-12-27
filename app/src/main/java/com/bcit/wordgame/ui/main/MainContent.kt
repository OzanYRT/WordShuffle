package com.bcit.wordgame.ui.main

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bcit.wordgame.WordGameViewModel
import com.bcit.wordgame.dictionary.Dictionary

enum class Screen(val route: String) {
    START("start"),
    GAMEOVER("gameover")
}

@Composable
fun MainContent(dictionary: Dictionary, viewModel: WordGameViewModel) {

val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.START.route,
        builder = {
            composable(Screen.START.route) {
                WordGrid(
                    dictionary = dictionary,
                    viewModel = viewModel)
            }
        })

}