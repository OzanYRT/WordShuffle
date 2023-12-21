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


}
