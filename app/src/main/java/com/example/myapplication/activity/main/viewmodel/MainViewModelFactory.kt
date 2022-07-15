package com.example.myapplication.activity.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.activity.main.helper.MainHelper
import com.example.myapplication.repository.MainRepository
import java.lang.IllegalArgumentException

class MainViewModelFactory(private val mainHelper: MainHelper): ViewModelProvider.Factory {

    override fun<T : ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(MainRepository(mainHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}