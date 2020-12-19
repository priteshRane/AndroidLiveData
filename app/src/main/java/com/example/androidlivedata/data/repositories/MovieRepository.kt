package com.example.androidlivedata.data.repositories

import com.example.androidlivedata.data.network.MyApiService
import com.example.androidlivedata.data.network.responses.MovieResponse
import retrofit2.Response

class MovieRepository constructor(private val myApiService: MyApiService) {

    suspend fun getMovies(page: Int, pageSize: Int): Response<MovieResponse> {
        return myApiService.api.movieResponse(page, pageSize)
    }
}