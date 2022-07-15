package com.example.myapplication.activity.main.helper

import com.example.myapplication.model.Post
import com.example.myapplication.network.service.PostService

class EditHelperImpl(private val postService: PostService): EditHelper {
    override suspend fun editPost(post: Post): Post {
        return postService.updatePost(post.id, post)
    }
}