package com.example.myapplication.activity.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.activity.main.intentstate.CreateIntent
import com.example.myapplication.activity.main.intentstate.CreateState
import com.example.myapplication.activity.main.intentstate.MainIntent
import com.example.myapplication.model.Post
import com.example.myapplication.repository.CreateRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class CreateViewModel(private val repository: CreateRepository) : ViewModel() {

    val createIntent = Channel<CreateIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<CreateState>(CreateState.Init)
    val state: StateFlow<CreateState> get() = _state

    var post: Post? = null

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            createIntent.consumeAsFlow().collect{
                when (it){
                    is CreateIntent.CreatePost -> createPost()
                }
            }
        }
    }

    private fun createPost(){
        viewModelScope.launch {
            _state.value = CreateState.Loading
            _state.value = try {
                CreateState.CreatePost(repository.createPost(post!!))
            }catch (e: Exception){
                CreateState.Error(e.localizedMessage)
            }
        }
    }

}