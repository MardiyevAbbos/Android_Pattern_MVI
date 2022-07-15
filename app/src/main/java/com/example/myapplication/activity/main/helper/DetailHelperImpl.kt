package com.example.myapplication.activity.main.helper

import com.example.myapplication.model.Post
import com.example.myapplication.network.service.PostService

class DetailHelperImpl(private val postService: PostService): DetailHelper {

    override suspend fun detailPost(id: Int): Post {
        return postService.singlePost(id)
    }

}