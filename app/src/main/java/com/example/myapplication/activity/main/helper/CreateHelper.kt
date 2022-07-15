package com.example.myapplication.activity.main.helper

import com.example.myapplication.model.Post

interface CreateHelper {
    suspend fun createPost(post: Post) : Post
}