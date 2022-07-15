package com.example.myapplication.repository

import com.example.myapplication.activity.main.helper.MainHelper

class MainRepository(private val mainHelper: MainHelper) {
    suspend fun allPosts() = mainHelper.allPosts()
    suspend fun deletePost(id: Int) = mainHelper.deletePost(id)
}