package com.example.myapplication.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.activity.main.helper.MainHelperImpl
import com.example.myapplication.activity.main.intentstate.MainIntent
import com.example.myapplication.activity.main.intentstate.MainState
import com.example.myapplication.activity.main.viewmodel.MainViewModel
import com.example.myapplication.activity.main.viewmodel.MainViewModelFactory
import com.example.myapplication.adapter.PostAdapter
import com.example.myapplication.model.Post
import com.example.myapplication.network.RetrofitBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var rv_posts:RecyclerView

    private var itemsLength:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        observerViewModel()
    }

    private fun observerViewModel() {
        lifecycleScope.launch{
            viewModel.state.collect{
                when (it){
                    is MainState.Init -> {
                        Log.d("TAGMainPage", "Init")
                    }
                    is MainState.Loading -> {
                        Log.d("TAGMainPage", "Loading")
                    }
                    is MainState.AllPosts -> {
                        Log.d("TAGMainPage", "AllPosts: ${it.posts}")
                        refreshAdapter(it.posts)
                        itemsLength = it.posts.size
                    }
                    is MainState.DeletePost -> {
                        Log.d("TAGMainPage", "DeletePost: ${it.post}")
                    }
                    is MainState.Error -> {
                        Log.d("TAGMainPage", "Error: ${it.error}")
                    }
                }
            }
        }
    }

    private fun initViews() {
        val factory = MainViewModelFactory(MainHelperImpl(RetrofitBuilder.POST_SERVICE))
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        rv_posts = findViewById(R.id.rv_posts)
        rv_posts.layoutManager = GridLayoutManager(this, 1)

        intentAllPost()

        val b_create = findViewById<FloatingActionButton>(R.id.b_floating)
        b_create.setOnClickListener{
            val intent = Intent(this, CreateActivity::class.java)
            intent.putExtra("length", itemsLength)
            launcher.launch(intent)
        }
    }

    fun callDetailPage(id: Int){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    fun callEditPage(post: Post){
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra("post", post)
        launcher.launch(intent)
    }

    private val launcher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback<ActivityResult> {
            if (it.resultCode == 10) {
                intentAllPost()
            }
        }
    )

    private fun refreshAdapter(posters: ArrayList<Post>) {
        val adapter = PostAdapter(this, posters)
        rv_posts.adapter = adapter
    }

    private fun intentAllPost(){
        lifecycleScope.launch {
            viewModel.mainIntent.send(MainIntent.AllPosts)
        }
    }

    fun intentDeletePost(id: Int){
        lifecycleScope.launch {
            viewModel.postId = id
            viewModel.mainIntent.send(MainIntent.DeletePost)
        }
    }

}