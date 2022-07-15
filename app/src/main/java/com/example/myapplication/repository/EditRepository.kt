package com.example.myapplication.repository

import com.example.myapplication.activity.main.helper.EditHelper
import com.example.myapplication.model.Post

class EditRepository(private val editHelper: EditHelper) {
    suspend fun editPost(post: Post) = editHelper.editPost(post)
}