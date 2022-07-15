package com.example.myapplication.repository

import com.example.myapplication.activity.main.helper.CreateHelper
import com.example.myapplication.model.Post

class CreateRepository(private val createHelper: CreateHelper) {
    suspend fun createPost(post: Post) = createHelper.createPost(post)
}