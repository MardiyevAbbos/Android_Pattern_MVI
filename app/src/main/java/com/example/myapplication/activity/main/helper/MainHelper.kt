package com.example.myapplication.activity.main.helper

import com.example.myapplication.model.Post

interface MainHelper {
    suspend fun allPosts(): ArrayList<Post>
    suspend fun deletePost(id: Int) : Post
}