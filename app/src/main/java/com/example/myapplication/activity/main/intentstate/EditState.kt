package com.example.myapplication.activity.main.intentstate

import com.example.myapplication.model.Post

sealed class EditState {
    object Init: EditState()
    object Loading: EditState()

    data class Error(val error: String?): EditState()

    data class EditPost(val post: Post): EditState()
}
