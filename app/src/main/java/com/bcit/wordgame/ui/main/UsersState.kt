package com.bcit.wordgame.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import com.bcit.wordgame.data.LocalUser
import com.bcit.wordgame.data.UsersRepository

class UsersState(private val usersRepository: UsersRepository) {

    // UI state
    var users by mutableStateOf(usersRepository.getAll().toMutableStateList())

    fun insertEntity(user: LocalUser) {
        usersRepository.insertEntity(user)
    }

    fun refresh() {
        users = usersRepository.getAll().toMutableStateList()
    }

    fun delete(user: LocalUser) {
        users.remove(user)
        usersRepository.delete(user)
    }
}