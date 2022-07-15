package com.example.myapplication.activity.main.helper

import com.example.myapplication.model.Post
import com.example.myapplication.network.service.PostService

class CreateHelperImpl(private val postService: PostService): CreateHelper {

    override suspend fun createPost(post: Post): Post {
        return postService.createPost(post)
    }

}