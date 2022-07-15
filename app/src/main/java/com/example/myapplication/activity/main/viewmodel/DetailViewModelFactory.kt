package com.example.myapplication.activity.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.activity.main.helper.DetailHelper
import com.example.myapplication.activity.main.helper.EditHelper
import com.example.myapplication.repository.DetailRepository
import com.example.myapplication.repository.EditRepository
import java.lang.IllegalArgumentException

class DetailViewModelFactory(private val detailHelper: DetailHelper): ViewModelProvider.Factory {

    override fun<T : ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(DetailRepository(detailHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}