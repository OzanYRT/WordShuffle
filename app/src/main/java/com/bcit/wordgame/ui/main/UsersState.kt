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

    fun checkUser(user: LocalUser): Boolean {
        return usersRepository.checkUser(user)
    }

    fun getTop10(): List<LocalUser> {
        return usersRepository.getTop10()
    }

    fun deleteAll() {
        usersRepository.deleteAll()
    }

    fun checkDuplicate(user: LocalUser): Boolean {
        return usersRepository.checkDuplicate(user)
    }
}