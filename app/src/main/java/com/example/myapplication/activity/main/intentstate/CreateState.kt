package com.example.myapplication.activity.main.intentstate

import com.example.myapplication.model.Post

sealed class CreateState {
    object Init: CreateState()
    object Loading: CreateState()

    data class Error(val error: String?): CreateState()

    data class CreatePost(val post: Post): CreateState()
}
