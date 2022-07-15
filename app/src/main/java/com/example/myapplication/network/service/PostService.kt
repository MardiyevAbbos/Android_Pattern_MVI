package com.example.myapplication.network.service

import com.example.myapplication.model.Post
import retrofit2.Call
import retrofit2.http.*

interface PostService {

    @GET("posts")
    suspend fun allPosts(): ArrayList<Post>

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Int): Post

    @GET("posts/{id}")
    suspend fun singlePost(@Path("id") id: Int): Post

    @POST("posts")
    suspend fun createPost(@Body post: Post): Post

    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") id: Int, @Body post: Post): Post

}