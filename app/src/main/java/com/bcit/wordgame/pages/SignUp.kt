package com.bcit.wordgame.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bcit.wordgame.data.LocalUser
import com.bcit.wordgame.ui.main.CustomTextField
import com.bcit.wordgame.ui.main.SignupState
import com.bcit.wordgame.ui.main.UsersState

@Composable
fun SignUp(usersState: UsersState) {
    val signupState = remember { SignupState() }
    Column {
        Column {
            Text(text = "name", fontSize = 18.sp)
            CustomTextField(value = signupState.name, onValueChanged = signupState.onNameChanged)
            Text(text = "email", fontSize = 18.sp)
            CustomTextField(value = signupState.email, onValueChanged = signupState.onEmailChanged)
            Text(text = "password", fontSize = 18.sp)
            CustomTextField(value = signupState.password, onValueChanged = signupState.onPasswordChanged)
        }
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                val user = LocalUser(
                    uid = signupState.uid.toIntOrNull(),
                    name = signupState.name,
                    email = signupState.email,
                    password = signupState.password,
                    highscore = signupState.highScore
                )
                usersState.insertEntity(user)
            }) {
                Text(text = "Sign in", fontSize = 30.sp)
            }
            LazyColumn {
                items(usersState.users.toMutableStateList().size) {index ->
                    UserItem(
                        user = usersState.users[index]
                    )
                }
            }
        }
    }
}

@Composable
fun UserItem(
    user: LocalUser
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .height(80.dp)
            .background(Color(0xFFD33535)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = user.name ?: "", color = Color.Black, fontSize = 20.sp)
    }
}