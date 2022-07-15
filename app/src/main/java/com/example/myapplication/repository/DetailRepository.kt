package com.example.myapplication.repository

import com.example.myapplication.activity.main.helper.DetailHelper

class DetailRepository(private val detailHelper: DetailHelper) {
    suspend fun detailPost(id: Int) = detailHelper.detailPost(id)
}