package com.bcit.wordgame.pages

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.bcit.wordgame.ui.main.UserItem
import com.bcit.wordgame.ui.main.UsersState

@Composable
fun LeaderBoard(usersState: UsersState) {
    val top10 = usersState.getTop10()
    LazyColumn {
        items(top10.size) {index ->
            UserItem(top10[index])
        }
    }
}