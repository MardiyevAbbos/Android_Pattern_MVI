package com.example.myapplication.activity.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.activity.main.intentstate.*
import com.example.myapplication.model.Post
import com.example.myapplication.repository.EditRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class EditViewModel(private val repository: EditRepository) : ViewModel() {

    val editIntent = Channel<EditIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<EditState>(EditState.Init)
    val state: StateFlow<EditState> get() = _state

    var post: Post? = null

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            editIntent.consumeAsFlow().collect{
                when (it){
                    is EditIntent.EditPost -> editPost()
                }
            }
        }
    }

    private fun editPost(){
        viewModelScope.launch {
            _state.value = EditState.Loading
            _state.value = try {
                EditState.EditPost(repository.editPost(post!!))
            }catch (e: Exception){
                EditState.Error(e.localizedMessage)
            }
        }
    }

}