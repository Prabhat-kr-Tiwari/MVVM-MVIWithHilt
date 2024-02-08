package com.example.mvvmormviwithhilt.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mvvmormviwithhilt.R
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.mvvmormviwithhilt.databinding.FragmentMainBinding
import com.example.mvvmormviwithhilt.model.Blog
import com.example.mvvmormviwithhilt.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment
    constructor(
        private val someString: String
    )
    :Fragment(R.layout.fragment_main) {

    private  val TAG = "AppDebug"
    private val viewModel:MainViewModel by viewModels()
    lateinit var binding :FragmentMainBinding
    lateinit var text:TextView
    lateinit var progressBar:ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ${someString}")

         text = binding.text
       progressBar=binding.progressBar

        subscribeObserver()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)
    }


        private fun subscribeObserver() {

            viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
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