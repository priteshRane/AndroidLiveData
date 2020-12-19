package com.example.androidlivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidlivedata.data.network.MyApiService
import com.example.androidlivedata.data.network.NetworkConnectionInterceptor
import com.example.androidlivedata.data.repositories.MovieRepository
import com.example.androidlivedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    lateinit var viewModel: MainViewModel
    lateinit var viewModelFactory: MainViewModelFactory
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val myApiService = MyApiService(networkConnectionInterceptor)
        val movieRepository = MovieRepository(myApiService)

        viewModelFactory = MainViewModelFactory(movieRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.getMovieList()
        viewModel.movies.observe(this, Observer {
            Log.i(TAG, it.toString())
        })
    }
}