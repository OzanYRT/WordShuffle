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

    fun deleteAll() {
        val userList = getAll()
        for(elements in userList) {
            userDao.delete(elements)
        }
    }

    fun checkUser(user: LocalUser): Boolean {
        val userList = getAll()
        for(element in userList) {
            if(element.name == user.name && element.email == user.email &&
                element.password == user.password) {
                return true
            }
        }
        return false
    }

    fun getTop10(): List<LocalUser> {
        val userList = getAll()
        return userList.sortedByDescending { it.highscore }.take(10)
    }

    fun checkDuplicate(user: LocalUser): Boolean {
        val userList = getAll()
        for(elements in userList) {
            if(elements.name == user.name) {
                return false
            }
        }
        return true
    }

}