package com.example.myapplication.activity.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.activity.main.helper.CreateHelper
import com.example.myapplication.activity.main.helper.MainHelper
import com.example.myapplication.repository.CreateRepository
import com.example.myapplication.repository.MainRepository
import java.lang.IllegalArgumentException

class CreateViewModelFactory(private val createHelper: CreateHelper): ViewModelProvider.Factory {

    override fun<T : ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(CreateViewModel::class.java)){
            return CreateViewModel(CreateRepository(createHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}