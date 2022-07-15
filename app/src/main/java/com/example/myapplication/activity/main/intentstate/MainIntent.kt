package com.example.myapplication.activity.main.intentstate

sealed class MainIntent {
    object AllPosts: MainIntent()
    object DeletePost: MainIntent()
}