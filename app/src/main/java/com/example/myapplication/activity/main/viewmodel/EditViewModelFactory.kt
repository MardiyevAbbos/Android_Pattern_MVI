package com.example.myapplication.activity.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.activity.main.helper.EditHelper
import com.example.myapplication.repository.EditRepository
import java.lang.IllegalArgumentException

class EditViewModelFactory(private val editHelper: EditHelper): ViewModelProvider.Factory {

    override fun<T : ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(EditViewModel::class.java)){
            return EditViewModel(EditRepository(editHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}