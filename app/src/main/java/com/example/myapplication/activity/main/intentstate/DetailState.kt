package com.example.myapplication.activity.main.intentstate

import com.example.myapplication.model.Post


sealed class DetailState {
    object Init: DetailState()
    object Loading: DetailState()

    data class Error(val error: String?): DetailState()

    data class DetailPost(val post: Post): DetailState()
}
