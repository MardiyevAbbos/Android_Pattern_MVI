package com.example.myapplication.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.activity.main.helper.CreateHelperImpl
import com.example.myapplication.activity.main.intentstate.CreateIntent
import com.example.myapplication.activity.main.intentstate.CreateState
import com.example.myapplication.activity.main.intentstate.MainState
import com.example.myapplication.activity.main.viewmodel.CreateViewModel
import com.example.myapplication.activity.main.viewmodel.CreateViewModelFactory
import com.example.myapplication.model.Post
import com.example.myapplication.network.RetrofitBuilder
import kotlinx.coroutines.launch

class CreateActivity : AppCompatActivity() {
    private lateinit var viewModel: CreateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        initViews()
        observerViewModel()

    }

    private fun observerViewModel() {
        lifecycleScope.launch{
            viewModel.state.collect{
                when (it){
                    is CreateState.Init -> {
                        Log.d("TAGCreatePage", "Init")
                    }
                    is CreateState.Loading -> {
                        Log.d("TAGCreatePage", "Loading")
                    }
                    is CreateState.CreatePost -> {
                        Log.d("TAGCreatePage", "AllPosts: ${it.post}")
                        val intent = Intent()
                        setResult(10, intent)
                        finish()
                    }
                    is CreateState.Error -> {
                        Log.d("TAGCreatePage", "Error: ${it.error}")
                    }
                }
            }
        }
    }


    private fun initViews() {
        val factory = CreateViewModelFactory(CreateHelperImpl(RetrofitBuilder.POST_SERVICE))
        viewModel = ViewModelProvider(this, factory)[CreateViewModel::class.java]

        val edt_title = findViewById<EditText>(R.id.edt_title)
        val edt_body = findViewById<EditText>(R.id.edt_message)
        val b_create = findViewById<Button>(R.id.b_create_post)

        b_create.setOnClickListener {
            if (edt_title.text.isNotEmpty() && edt_body.text.isNotEmpty()){
                val length = intent.getIntExtra("length", 0)
                val post = Post(length, length, edt_title.text.toString(), edt_body.text.toString())
                intentCreatePost(post)
            }
        }
    }

    private fun intentCreatePost(post: Post){
        lifecycleScope.launch {
            viewModel.post = post
            viewModel.createIntent.send(CreateIntent.CreatePost)
        }
    }

}