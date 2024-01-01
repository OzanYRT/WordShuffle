package com.bcit.wordgame.data

class UsersRepository(private val userDao: UserDao) {

    fun insertEntity(user: LocalUser) {
        userDao.insert(user)
    }

    fun getAll(): List<LocalUser> {
        return userDao.getAll()
    }

    fun delete(user: LocalUser) {
        userDao.delete(user)
    }

}