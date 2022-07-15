package com.example.myapplication.activity.main.intentstate

import com.example.myapplication.model.Post

sealed class MainState {
    object Init: MainState()
    object Loading: MainState()

    data class Error(val error: String?): MainState()

    data class AllPosts(val posts: ArrayList<Post>): MainState()
    data class DeletePost(val post: Post): MainState()
}
