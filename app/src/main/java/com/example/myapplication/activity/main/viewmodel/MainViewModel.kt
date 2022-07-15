package com.example.myapplication.activity.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.activity.main.intentstate.MainIntent
import com.example.myapplication.activity.main.intentstate.MainState
import com.example.myapplication.repository.MainRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository): ViewModel() {

    val mainIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainState>(MainState.Init)
    val state: StateFlow<MainState> get() = _state

    var postId: Int? = null

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            mainIntent.consumeAsFlow().collect{
                when (it){
                    is MainIntent.AllPosts -> apiAllPosts()
                    is MainIntent.DeletePost -> apiDeletePost()
                }
            }
        }
    }

    private fun apiAllPosts(){
        viewModelScope.launch {
            _state.value = MainState.Loading
            _state.value = try {
                MainState.AllPosts(repository.allPosts())
            }catch (e: Exception){
                MainState.Error(e.localizedMessage)
            }
        }
    }

    private fun apiDeletePost(){
        viewModelScope.launch {
            _state.value = MainState.Loading
            _state.value = try {
                MainState.DeletePost(repository.deletePost(postId!!))
            }catch (e: Exception){
                MainState.Error(e.localizedMessage)
            }
        }
    }

}