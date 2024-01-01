package com.bcit.wordgame.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class SignupState {
    var uid by mutableStateOf("")
    val onUidChanged: (String) -> Unit = {
        uid = it
    }

    var name by mutableStateOf("")
    val onNameChanged:(String)->Unit = {
        name = it
    }

    var email by mutableStateOf("")
    val onEmailChanged:(String)->Unit = {
        email = it
        validEmail = email.contains("@")
    }
    var validEmail = false

    var password by mutableStateOf("")
    val onPasswordChanged:(String)->Unit = {
        password = it
    }

    var highScore by mutableIntStateOf(0)
    val onHighScoreChanged:(Int)->Unit = {
        highScore = it
    }
}