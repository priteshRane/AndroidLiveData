package com.example.androidlivedata

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidlivedata.data.network.responses.Movie
import com.example.androidlivedata.data.repositories.MovieRepository
import com.example.androidlivedata.util.Coroutines
import com.example.androidlivedata.util.NoInternetException

class MainViewModel constructor(private val movieRepository: MovieRepository) : ViewModel() {

    val TAG = "MovieListViewModel"
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    fun getMovieList(): LiveData<List<Movie>> {
        Log.i(TAG, "Get Movies")
        if (movies.value == null) {
            getMoviesFromRepository()
        }
        return movies
    }

    private fun getMoviesFromRepository() {
        Coroutines.main {
            try {
                val movieResponse = movieRepository.getMovies(1, 10)
                if (movieResponse.isSuccessful) {
                    _movies.value = movieResponse.body()?.movie
                }
            } catch (e: NoInternetException) {
                Log.i(TAG, e.toString())
            } catch (e: Exception) {
                Log.i(TAG, e.toString())
            }
        }
    }
}