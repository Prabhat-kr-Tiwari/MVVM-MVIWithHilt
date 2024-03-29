package com.example.mvvmormviwithhilt.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.mvvmormviwithhilt.databinding.ActivityMainBinding
import com.example.mvvmormviwithhilt.model.Blog
import com.example.mvvmormviwithhilt.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "AppDebug"
    private val viewModel: MainViewModel by viewModels()
    private lateinit var text: TextView
    private lateinit var progressBar: ProgressBar
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        text = binding.text
        progressBar=binding.progressBar

        subscribeObserver()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)
    }


    private fun subscribeObserver() {

        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<Blog>> -> {
                    displayProgressBar(false)
                    appendBlogTitles(dataState.data)

                }

                is DataState.Error -> {

                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }

                is DataState.Loading -> {
                    displayProgressBar(true)

                }
            }
        })

    }

    private fun displayError(message: String?) {

        if (message != null) {
            text.text = message


        }else{
            text.text="Unknown error"
        }

    }

    private fun displayProgressBar(isDisplayed:Boolean){
        progressBar.visibility=if (isDisplayed) View.VISIBLE else View.GONE


    }

    private fun appendBlogTitles(blogs:List<Blog>){
        val sb=StringBuilder()
        for (blog in blogs){

            sb.append(blog.title+"\n")

        }
        text.text=sb.toString()

    }
}