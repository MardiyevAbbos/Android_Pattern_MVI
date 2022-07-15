package com.example.myapplication.activity.main.helper

import com.example.myapplication.model.Post

interface EditHelper {
    suspend fun editPost(post: Post) : Post
}