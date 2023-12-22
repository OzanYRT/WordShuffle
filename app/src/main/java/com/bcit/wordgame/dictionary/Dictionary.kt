package com.bcit.wordgame.dictionary

import android.content.Context
import java.io.File

class Dictionary(private val context: Context) {

    val dictionary: List<String> by lazy {
        context.assets.open("dictionary.txt").bufferedReader().use { it.readLines() }
    }

    fun checkWord(word: String): Boolean {
        var left = 0
        var right = dictionary.size - 1
        val searchWord = word.lowercase()

        while (left <= right) {
            val mid = (left + right) / 2
            when {
                dictionary[mid] < searchWord -> left = mid + 1
                dictionary[mid] > searchWord -> right = mid - 1
                else -> return true
            }
        }
        return false
    }

    fun graphifyAndFindWords(letters: List<String>, gridSize: Int): Set<String> {
        val dx = listOf(-1, -1, -1, 0, 1, 1, 1, 0)
        val dy = listOf(-1, 0, 1, 1, 1, 0, -1, -1)
        val validWords = mutableSetOf<String>()

        fun isValidCoordinate(x: Int, y: Int, size: Int): Boolean {
            return x in 0 until size && y in 0 until size
        }

        fun dfs(x: Int, y: Int, visited: Array<BooleanArray>, currentWord: String) {
            if (currentWord.length > 10) return
            if(!isValidCoordinate(x, y, gridSize) || visited[x][y]) {
                return
            }
            val newWord = currentWord + letters[x * gridSize + y]
            if(checkWord(newWord)) {
                validWords.add(newWord)
            }

            visited[x][y] = true
            for(i in 0 until 8) {
                val newX = x + dx[i]
                val newY = y + dy[i]
                dfs(newX, newY, visited, newWord)
            }
            visited[x][y] = false
        }

        val mid = gridSize / 2
        val visited = Array(gridSize) { BooleanArray(gridSize) }
        dfs(mid, mid, visited, "")

        return validWords
    }


}
