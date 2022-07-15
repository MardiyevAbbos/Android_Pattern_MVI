package com.example.myapplication.activity.main.helper

import com.example.myapplication.model.Post
import com.example.myapplication.network.service.PostService

class MainHelperImpl(private val postService: PostService): MainHelper {

    override suspend fun allPosts(): ArrayList<Post> {
        return postService.allPosts()
    }

    override suspend fun deletePost(id: Int): Post {
        return postService.deletePost(id)
    }

}