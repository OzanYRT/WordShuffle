package com.bcit.wordgame.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bcit.wordgame.data.LocalUser

@Composable
fun UserItem(
    user: LocalUser
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .height(80.dp)
            .background(Color(0xFF7C48B8)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = user.name ?: "", color = Color.Black, fontSize = 20.sp)
        Text(text = user.highscore.toString() ?: "", color = Color.Black, fontSize = 20.sp)
    }
}