package com.bcit.wordgame.pages

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bcit.wordgame.WordGameViewModel
import com.bcit.wordgame.dictionary.Dictionary
import com.bcit.wordgame.ui.main.UsersState

enum class Screen(val route: String) {
    MENU("menu"),
    START("start"),
    GAMEOVER("gameOver"),
    SIGNUP("signup")
}

@Composable
fun MainContent(usersState: UsersState, dictionary: Dictionary, viewModel: WordGameViewModel) {

val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SIGNUP.route,
        builder = {
            composable(Screen.MENU.route) {
                MainMenu(nav = navController)
            }
            composable(Screen.START.route) {
                WordGrid(
                    dictionary = dictionary,
                    viewModel = viewModel,
                    nav = navController)
            }
            composable(Screen.GAMEOVER.route) {
                GameOver(
                    viewModel = viewModel,
                    nav = navController
                )
            }
            composable(Screen.SIGNUP.route) {
                SignUp(usersState = usersState)
            }
        })

}