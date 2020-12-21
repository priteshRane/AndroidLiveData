package com.example.androidlivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.androidlivedata.data.network.MyApiService
import com.example.androidlivedata.data.network.NetworkConnectionInterceptor
import com.example.androidlivedata.data.repositories.MovieRepository
import com.example.androidlivedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
    private val myApiService = MyApiService(networkConnectionInterceptor)
    private val movieRepository = MovieRepository(myApiService)

    lateinit var viewModelFactory: MainViewModelFactory
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelFactory = MainViewModelFactory(movieRepository)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }
}