package com.example.myapplication.activity.main.helper

import com.example.myapplication.model.Post

interface DetailHelper {
    suspend fun detailPost(id: Int) : Post
}